package com.intuit.controller;

import com.intuit.entry.ProductEntry;
import com.intuit.response.ProductResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public interface ProductController {

    @GetMapping("/{productId}")
    ProductResponse getProductsById(@PathVariable Long productId);

    @PostMapping("/addProduct")
    ProductResponse addProduct(@RequestBody ProductEntry productEntry);

    @GetMapping("/getProductsByCategory/{category}")
    ProductResponse getProductsByCategory(@PathVariable String category);

    @GetMapping("/getAllProducts")
    ProductResponse getAllProducts();

}
