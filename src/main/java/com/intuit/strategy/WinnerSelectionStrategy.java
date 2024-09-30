package com.intuit.strategy;

import com.intuit.entry.BidEntry;
import java.util.List;

public interface WinnerSelectionStrategy {

    Long determineWinner(List<BidEntry> bids);
}