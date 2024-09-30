package com.intuit.controller.impl;

import com.intuit.controller.BidController;
import com.intuit.entry.BidEntry;
import com.intuit.entry.UserEntry;
import com.intuit.exception.EntityNotFoundException;
import com.intuit.manager.BidManager;
import com.intuit.response.BidResponse;
import com.intuit.response.StatusResponse;
import com.intuit.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
public class BidControllerImpl implements BidController {

    @Autowired
    private BidManager bidManager;

    @Override
    public ResponseEntity<BidResponse> getBidById(Long bidId) {

        BidResponse response = new BidResponse();
        try {
            BidEntry bidEntry = bidManager.getBidById(bidId);
            response.setData(Collections.singletonList(bidEntry));
            response.setStatus(new StatusResponse(1, StatusResponse.Type.SUCCESS, Objects.isNull(bidEntry) ? 0 : 1));
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            response.setStatus(new StatusResponse(0, "Bid not found", StatusResponse.Type.ERROR));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            response.setStatus(new StatusResponse(0, "An unexpected error occurred", StatusResponse.Type.ERROR));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Override
    public ResponseEntity<BidResponse> placeBid(BidEntry entry) {
        BidResponse response = new BidResponse();
        try {
            BidEntry bidEntry = bidManager.placeBid(entry);
            response.setData(Collections.singletonList(bidEntry));
            response.setStatus(new StatusResponse(1, StatusResponse.Type.SUCCESS, Objects.isNull(bidEntry) ? 0 : 1));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setStatus(new StatusResponse(0, e.getMessage(), StatusResponse.Type.ERROR));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Override
    public ResponseEntity<BidResponse> getBidsByProduct(Long productId) {
        BidResponse response = new BidResponse();

        try {
            List<BidEntry> bidEntries = bidManager.getBidsForProduct(productId);
            response.setData(bidEntries);
            response.setStatus(new StatusResponse(1, StatusResponse.Type.SUCCESS, Objects.isNull(bidEntries) ? 0 : bidEntries.size()));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setStatus(new StatusResponse(0, e.getMessage(), StatusResponse.Type.ERROR));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Override
    public ResponseEntity<UserResponse> determineWinner(Long productId) {
        UserResponse response = new UserResponse();
        try {
            UserEntry entry = bidManager.determineWinner(productId);
            response.setData(Collections.singletonList(entry));
            response.setStatus(new StatusResponse(1, StatusResponse.Type.SUCCESS, Objects.isNull(entry) ? 0 : 1));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setStatus(new StatusResponse(0, e.getMessage(), StatusResponse.Type.ERROR));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
