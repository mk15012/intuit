package com.intuit.controller.impl;

import com.intuit.controller.ProductController;
import com.intuit.entry.ProductEntry;
import com.intuit.entry.UserEntry;
import com.intuit.enums.UserType;
import com.intuit.manager.ProductManager;
import com.intuit.manager.UserManager;
import com.intuit.response.ProductResponse;
import com.intuit.response.StatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
public class ProductControllerImpl implements ProductController {

    @Autowired
    private ProductManager productManager;

    @Autowired
    private UserManager userManager;

    public ProductControllerImpl(ProductManager productManager) {
        this.productManager = productManager;
    }

    @Override
    public ProductResponse addProduct(ProductEntry productEntry) {
        ProductResponse response = new ProductResponse();
        try {
            validateRequest(productEntry);
            productManager.addProduct(productEntry);
            response.setData(Collections.singletonList(productEntry));
            response.setStatus(new StatusResponse(1, StatusResponse.Type.SUCCESS, 1));
        } catch (Exception e) {
            response.setStatus(new StatusResponse(0, e.getMessage(), StatusResponse.Type.ERROR));
        }
        return response;
    }

    @Override
    public ProductResponse getProductsByCategory(String category) {

        ProductResponse response = new ProductResponse();
        try {
            List<ProductEntry> productEntries = productManager.getProductsByCategory(category);
            response.setData(productEntries);
            response.setStatus(new StatusResponse(1, StatusResponse.Type.SUCCESS, Objects.isNull(productEntries) ? 0 : 1));
        } catch (Exception e) {
            response.setStatus(new StatusResponse(0, e.getMessage(), StatusResponse.Type.ERROR));
        }
        return response;
    }

    @Override
    public ProductResponse getProductsById(Long productId) {

        ProductResponse response = new ProductResponse();
        try {
            ProductEntry product = productManager.getProductById(productId);
            response.setData(Collections.singletonList(product));
            response.setStatus(new StatusResponse(1, StatusResponse.Type.SUCCESS, Objects.isNull(product) ? 0 : 1));
        } catch (Exception e) {
            response.setStatus(new StatusResponse(0, e.getMessage(), StatusResponse.Type.ERROR));
        }
        return response;
    }

    @Override
    public ProductResponse getAllProducts() {

        ProductResponse response = new ProductResponse();
        try {
            List<ProductEntry> productEntries = productManager.getAllProducts();
            response.setData(productEntries);
            response.setStatus(new StatusResponse(1, StatusResponse.Type.SUCCESS, Objects.isNull(productEntries) ? 0 : 1));
        } catch (Exception e) {
            response.setStatus(new StatusResponse(0, e.getMessage(), StatusResponse.Type.ERROR));
        }
        return response;
    }

    private void validateRequest(ProductEntry productEntry) throws Exception {
        UserEntry userEntry = userManager.getUserById(productEntry.getUserId());
        if (!UserType.VENDOR.equals(userEntry.getUserType())) {
            throw new Exception("Only vendors can add products.");
        }

    }
}