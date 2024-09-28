package com.example.intuit.manager;

import com.example.intuit.entities.Product;
import com.example.intuit.entry.ProductEntry;
import com.example.intuit.enums.Category;
import com.example.intuit.manager.impl.ProductManagerImpl;
import com.example.intuit.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductManagerImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductManagerImpl productManager;

    @Test
    public void testFindEndedSlots() {
        ProductEntry product = new ProductEntry();
        product.setId(1L);
        product.setName("Test Product");
        product.setCategory(Category.JEWELLERY);

        when(productRepository.findEndedSlots(any(LocalDateTime.class))).thenReturn(Collections.singletonList(convertToEntity(product)));

        List<ProductEntry> result = productManager.findEndedSlots(LocalDateTime.now());
        assertEquals(1, result.size());
        assertEquals("Test Product", result.get(0).getName());
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

}