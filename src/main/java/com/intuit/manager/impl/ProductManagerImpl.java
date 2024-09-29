package com.intuit.manager.impl;

import com.intuit.entities.Product;
import com.intuit.entry.ProductEntry;
import com.intuit.enums.Category;
import com.intuit.exception.EntityNotFoundException;
import com.intuit.manager.ProductManager;
import com.intuit.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductManagerImpl implements ProductManager {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductEntry getProductById(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));
        return convertToEntry(product);
    }

    @Override
    public void addProduct(ProductEntry productEntry) {
        Product product = convertToEntity(productEntry);
        productRepository.save(product);
    }

    @Override
    public List<ProductEntry> getProductsByCategory(String category) {
        List<Product> productList = productRepository.getProductsByCategory(category);

        List<ProductEntry> result = new ArrayList<>();
        productList.forEach(product -> result.add(convertToEntry(product)));
        return result;
    }

    @Override
    public List<ProductEntry> findEndedSlots(LocalDateTime currentTime) {
        List<Product> products = productRepository.findEndedSlots(currentTime);
        return products.stream().map(this::convertToEntry).collect(Collectors.toList());
    }

    @Override
    public List<ProductEntry> getAllProducts() {
        List<Product> productList = productRepository.getAllProducts();
        List<ProductEntry> result = new ArrayList<>();
        for (Product product : productList) {
            result.add(convertToEntry(product));
        }
        return result;
    }

    private Product convertToEntity(ProductEntry productEntry) {
        Product product = new Product();
        product.setName(productEntry.getName());
        product.setBasePrice(productEntry.getBasePrice());
        product.setCategory(productEntry.getCategory().name());
        product.setSlotStart(productEntry.getStartTime());
        product.setSlotEnd(productEntry.getEndTime());
        return product;
    }

    private ProductEntry convertToEntry(Product product) {
        ProductEntry productEntry = new ProductEntry();
        productEntry.setName(product.getName());
        productEntry.setBasePrice(product.getBasePrice());
        productEntry.setCategory(Category.valueOf(product.getCategory()));
        productEntry.setStartTime(product.getSlotStart());
        productEntry.setEndTime(product.getSlotEnd());
        return productEntry;
    }

}
