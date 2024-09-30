package com.intuit.util;

import com.intuit.entry.BidEntry;
import com.intuit.entry.ProductEntry;
import com.intuit.entry.UserEntry;
import com.intuit.manager.BidManager;
import com.intuit.manager.ProductManager;
import com.intuit.manager.UserManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
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
    public void testCheckForEndedSlots() throws Exception {
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
        when(bidManager.determineWinner(1L)).thenReturn(user);

        schedulerService.checkForEndedSlots();

        verify(productManager, times(1)).findEndedSlots(any(LocalDateTime.class));
        verify(bidManager, times(1)).determineWinner(1L);
    }
}