package com.intuit.entry;

import com.intuit.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductEntry {

    private Long id;
    private String name;
    private Category category;
    private double basePrice;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long userId;

}