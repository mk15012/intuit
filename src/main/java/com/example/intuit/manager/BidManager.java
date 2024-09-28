package com.example.intuit.manager;

import com.example.intuit.entry.BidEntry;
import com.example.intuit.entry.UserEntry;

import java.util.List;

public interface BidManager {

    BidEntry getBidById(Long bidId);

    BidEntry placeBid(BidEntry bidEntry) throws Exception;

    List<BidEntry> getBidsForProduct(Long productId);

    UserEntry determineWinner(Long productId);
}
