package com.intuit.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "bid")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bid extends BaseEntity {

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "user_id")
    private Long userId;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "bid_time", nullable = false, insertable = true, updatable = true)
    private LocalDateTime bidTime;

    @Column(name = "communication_sent", nullable = false)
    private boolean communicationSent;

    protected void onCreate() {
        super.onCreate();
        if (this.bidTime == null) {
            this.bidTime = LocalDateTime.now();
        }
    }
}