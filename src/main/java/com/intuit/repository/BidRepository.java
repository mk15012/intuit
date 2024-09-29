package com.intuit.repository;


import com.intuit.entities.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid, Long> {

    @Query(value = "select * from bid where product_id = :productId", nativeQuery = true)
    List<Bid> getBidsForProduct(Long productId);

}