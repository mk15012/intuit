package com.example.intuit.repository;


import com.example.intuit.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select * from product", nativeQuery = true)
    List<Product> getAllProducts();

    @Query(value = "select * from product where category = :category", nativeQuery = true)
    List<Product> getProductsByCategory(String category);

    @Query(value = "select * FROM product WHERE slot_end < :currentTime", nativeQuery = true)
    List<Product> findEndedSlots(LocalDateTime currentTime);


}
