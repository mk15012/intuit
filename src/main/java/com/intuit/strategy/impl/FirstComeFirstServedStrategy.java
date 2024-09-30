package com.intuit.strategy.impl;

import com.intuit.entry.BidEntry;
import com.intuit.strategy.WinnerSelectionStrategy;

import java.util.List;

public class FirstComeFirstServedStrategy implements WinnerSelectionStrategy {
    @Override
    public Long determineWinner(List<BidEntry> bids) {
        return bids.stream()
                .filter(bid -> bid.getBidTime() != null)
                .min((bid1, bid2) -> bid1.getBidTime().compareTo(bid2.getBidTime()))
                .map(BidEntry::getUserId)
                .orElse(null);
    }
}