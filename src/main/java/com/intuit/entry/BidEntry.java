package com.intuit.entry;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BidEntry {

    private Long id;
    private Long productId;
    private Long userId;
    private double amount;
    private LocalDateTime bidTime;
    private boolean communicationSent;

}