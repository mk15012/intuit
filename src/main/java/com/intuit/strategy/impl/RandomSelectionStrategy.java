package com.intuit.strategy.impl;

import com.intuit.entry.BidEntry;
import com.intuit.strategy.WinnerSelectionStrategy;

import java.util.List;
import java.util.Random;

public class RandomSelectionStrategy implements WinnerSelectionStrategy {
    private final Random random = new Random();

    @Override
    public Long determineWinner(List<BidEntry> bids) {
        if (bids.isEmpty()) {
            return null;
        }
        return bids.get(random.nextInt(bids.size())).getUserId();
    }
}