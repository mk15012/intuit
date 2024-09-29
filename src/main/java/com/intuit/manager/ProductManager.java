package com.intuit.manager;

import com.intuit.entry.ProductEntry;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductManager {

    void addProduct(ProductEntry product);

    List<ProductEntry> getProductsByCategory(String category);

    ProductEntry getProductById(Long productId);

    List<ProductEntry> getAllProducts();

    List<ProductEntry> findEndedSlots(LocalDateTime currentTime);

}
