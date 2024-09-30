package com.intuit.manager.impl;

import com.intuit.entities.Bid;
import com.intuit.entry.BidEntry;
import com.intuit.entry.ProductEntry;
import com.intuit.entry.UserEntry;
import com.intuit.exception.EntityNotFoundException;
import com.intuit.manager.BidManager;
import com.intuit.manager.ProductManager;
import com.intuit.manager.UserManager;
import com.intuit.repository.BidRepository;
import com.intuit.strategy.WinnerSelectionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class BidManagerImpl implements BidManager {

    @Autowired
    private ProductManager productManager;

    @Autowired
    @Qualifier("randomSelectionStrategy")
    private WinnerSelectionStrategy winnerSelectionStrategy;

    @Autowired
    private UserManager userManager;

    @Autowired
    private BidRepository bidRepository;

    public BidManagerImpl(ProductManager productManager, UserManager userManager) {
        this.productManager = productManager;
        this.userManager = userManager;
    }

    @Override
    public BidEntry getBidById(Long bidId) {
        Bid bid = bidRepository.findById(bidId).orElseThrow(() -> new EntityNotFoundException("Bid not found with id: " + bidId));
        return convertToEntry(bid);
    }

    @Override
    public BidEntry placeBid(BidEntry bidEntry) throws Exception {
        validateBiddingRequest(bidEntry);
        bidRepository.save(convertToEntity(bidEntry));
        return bidEntry;
    }

    @Override
    public List<BidEntry> getBidsForProduct(Long productId) {
        List<Bid> bidEntries = bidRepository.getBidsForProduct(productId);

        List<BidEntry> result = new ArrayList<>();
        bidEntries.forEach(bid -> result.add(convertToEntry(bid)));
        return result;
    }

    @Override
    public UserEntry determineWinner(Long productId) {
        List<BidEntry> bids = getBidsForProduct(productId);
        if (bids.isEmpty()) {
            return null;
        }

        double highestAmount = bids.stream()
                .mapToDouble(BidEntry::getAmount)
                .max()
                .orElse(0);

        List<BidEntry> eligibleBids = bids.stream()
                .filter(bid -> bid.getAmount() == highestAmount)
                .toList();

        Long userId = winnerSelectionStrategy.determineWinner(eligibleBids);
        if(Objects.isNull(userId))
            return null;

        return userManager.getUserById(userId);
    }

    @Override
    public void resendFailedCommunications() {
        List<Bid> failedBids = bidRepository.findByCommunicationSentFalse();
        for (Bid bid : failedBids) {
            UserEntry user = userManager.getUserById(bid.getUserId());
            if (isWinner(bid)) {
                sendNotification(user.getEmail(), "Congratulations! You won the bid for product " + bid.getProductId());
            } else {
                sendNotification(user.getEmail(), "Sorry, you couldn't make it for product " + bid.getProductId());
            }
            bid.setCommunicationSent(true);
            bidRepository.save(bid);
        }
    }

    @Override
    public boolean isCommunicationSent(Long productId) {
        List<Bid> bids = bidRepository.getBidsForProduct(productId);
        return bids.stream().anyMatch(Bid::isCommunicationSent);
    }

    @Override
    public void markCommunicationSent(Long productId) {
        List<Bid> bids = bidRepository.getBidsForProduct(productId);
        for (Bid bid : bids) {
            bid.setCommunicationSent(true);
            bidRepository.save(bid);
        }
    }

    private boolean isWinner(Bid bid) {
        UserEntry winner = determineWinner(bid.getProductId());
        return winner != null && winner.getId().equals(bid.getUserId());
    }

    private void sendNotification(String email, String message) {
        System.out.println("Sending email to: " + email);
        System.out.println("Message: " + message);
    }

    private void validateBiddingRequest(BidEntry bidEntry) throws Exception {

        UserEntry userEntry = userManager.getUserById(bidEntry.getUserId());
        if (Objects.isNull(userEntry)) {
            throw new Exception("No such user found");
        }

        ProductEntry productEntry = productManager.getProductById(bidEntry.getProductId());
        if(Objects.isNull(productEntry)) {
            throw new Exception("No such product found");
        }

        if (bidEntry.getAmount() < productEntry.getBasePrice()) {
            throw new Exception("Bid amount must be higher than the base price.");
        }

        if (bidEntry.getBidTime().isBefore(productEntry.getStartTime()) || bidEntry.getBidTime().isAfter(productEntry.getEndTime())) {
            throw new Exception("Bidding is not allowed outside the bidding time slot.");
        }
    }

    private Bid convertToEntity(BidEntry bidEntry) {
        Bid bid = new Bid();
        bid.setAmount(bidEntry.getAmount());
        bid.setProductId(bidEntry.getProductId());
        bid.setUserId(bidEntry.getUserId());
        return bid;

    }

    private BidEntry convertToEntry(Bid bid) {
        return BidEntry.builder()
                .id(bid.getId())
                .bidTime(bid.getBidTime())
                .userId(bid.getUserId())
                .amount(bid.getAmount())
                .productId(bid.getProductId())
                .communicationSent(bid.isCommunicationSent())
                .build();
    }
}