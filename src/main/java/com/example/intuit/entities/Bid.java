package com.example.intuit.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "bid")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bid extends BaseEntity {

    private double amount;
    private LocalDateTime bidTime;
    private Long productId;
    private Long userId;

}