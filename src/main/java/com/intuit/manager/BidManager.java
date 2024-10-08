package com.intuit.manager;

import com.intuit.entry.BidEntry;
import com.intuit.entry.UserEntry;

import java.util.List;

public interface BidManager {

    BidEntry getBidById(Long bidId);

    BidEntry placeBid(BidEntry bidEntry) throws Exception;

    List<BidEntry> getBidsForProduct(Long productId);

    UserEntry determineWinner(Long productId) throws Exception;

    boolean isCommunicationSent(Long productId);

    void markCommunicationSent(Long productId);
}
