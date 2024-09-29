package com.intuit.util;

import com.intuit.entry.ProductEntry;
import com.intuit.entry.UserEntry;
import com.intuit.manager.BidManager;
import com.intuit.manager.ProductManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class SchedulerService {

    @Autowired
    private ProductManager productManager;

    @Autowired
    private BidManager bidManager;

    @Scheduled(fixedRate = 60000) // Run every minute
    public void checkForEndedSlots() {
        List<ProductEntry> productList = productManager.findEndedSlots(LocalDateTime.now());
        for (ProductEntry product : productList) {
            UserEntry userEntry = bidManager.determineWinner(product.getId());
            if(Objects.isNull(userEntry)) {
                System.out.println("No winner for product: " + product.getName());
            } else {
                sendNotification(userEntry.getEmail(), product.getName(), userEntry.getId());
            }
        }
    }

    private void sendNotification(String email, String productName, Long userId) {
        System.out.println("Sending email to: " + email);
        System.out.println("Hooray! User " + userId + " have won the bid for the product : " + productName);
    }
}