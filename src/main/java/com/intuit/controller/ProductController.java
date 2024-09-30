package com.intuit.controller;

import com.intuit.entry.ProductEntry;
import com.intuit.response.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public interface ProductController {

    @GetMapping("/{productId}")
    ResponseEntity<ProductResponse> getProductsById(@PathVariable Long productId);

    @PostMapping("/addProduct")
    ResponseEntity<ProductResponse> addProduct(@RequestBody ProductEntry productEntry);

    @GetMapping("/getProductsByCategory/{category}")
    ResponseEntity<ProductResponse> getProductsByCategory(@PathVariable String category);

    @GetMapping("/getAllProducts")
    ResponseEntity<ProductResponse> getAllProducts(@RequestParam(defaultValue = "0") int offset,
                                                   @RequestParam(defaultValue = "-1") int fetchSize);

}
