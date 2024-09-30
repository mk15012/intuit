package com.intuit.repository;


import com.intuit.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM products LIMIT :fetchSize OFFSET :offset", nativeQuery = true)
    List<Product> getAllProducts(@Param("offset") int offset, @Param("fetchSize") int fetchSize);

    @Query(value = "select * from products where category = :category", nativeQuery = true)
    List<Product> getProductsByCategory(String category);

    @Query(value = "select * FROM products WHERE slot_end <= :currentTime", nativeQuery = true)
    List<Product> findEndedSlots(LocalDateTime currentTime);


}
