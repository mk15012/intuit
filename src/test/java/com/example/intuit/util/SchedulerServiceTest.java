package com.example.intuit.util;

import com.example.intuit.entry.BidEntry;
import com.example.intuit.entry.ProductEntry;
import com.example.intuit.entry.UserEntry;
import com.example.intuit.manager.BidManager;
import com.example.intuit.manager.ProductManager;
import com.example.intuit.manager.UserManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SchedulerServiceTest {

    @Mock
    private ProductManager productManager;

    @Mock
    private BidManager bidManager;

    @Mock
    private UserManager userManager;

    @InjectMocks
    private SchedulerService schedulerService;

    @Test
    public void testCheckForEndedSlots() {
        // Arrange
        ProductEntry product = new ProductEntry();
        product.setId(1L);
        product.setName("Test Product");

        BidEntry bid1 = new BidEntry();
        bid1.setAmount(100.0);
        bid1.setUserId(1L);

        BidEntry bid2 = new BidEntry();
        bid2.setAmount(200.0);
        bid2.setUserId(2L);

        UserEntry user = new UserEntry();
        user.setEmail("test@example.com");

        when(productManager.findEndedSlots(any(LocalDateTime.class))).thenReturn(Collections.singletonList(product));
        when(bidManager.getBidsForProduct(1L)).thenReturn(Arrays.asList(bid1, bid2));
        when(userManager.getUserById(2L)).thenReturn(user);

        // Act
        schedulerService.checkForEndedSlots();

        // Assert
        verify(productManager, times(1)).findEndedSlots(any(LocalDateTime.class));
        verify(bidManager, times(1)).getBidsForProduct(1L);
        verify(userManager, times(1)).getUserById(2L);
    }
}