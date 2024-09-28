package com.example.intuit.controller.impl;

import com.example.intuit.controller.BidController;
import com.example.intuit.entry.BidEntry;
import com.example.intuit.entry.UserEntry;
import com.example.intuit.exception.EntityNotFoundException;
import com.example.intuit.manager.BidManager;
import com.example.intuit.response.BidResponse;
import com.example.intuit.response.StatusResponse;
import com.example.intuit.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
public class BidControllerImpl implements BidController {

    @Autowired
    private BidManager bidManager;

    @Override
    public BidResponse getBidById(Long bidId) {

        BidResponse response = new BidResponse();
        try {
            BidEntry bidEntry = bidManager.getBidById(bidId);
            response.setData(Collections.singletonList(bidEntry));
            response.setStatus(new StatusResponse(1, StatusResponse.Type.SUCCESS, Objects.isNull(bidEntry) ? 0 : 1));
        } catch (EntityNotFoundException e) {
            response.setStatus(new StatusResponse(0, "Bid not found", StatusResponse.Type.ERROR));
        } catch (Exception e) {
            response.setStatus(new StatusResponse(0, "An unexpected error occurred", StatusResponse.Type.ERROR));
        }
        return response;
    }

    @Override
    public BidResponse placeBid(BidEntry entry) {
        BidResponse response = new BidResponse();
        try {
            BidEntry bidEntry = bidManager.placeBid(entry);
            response.setData(Collections.singletonList(bidEntry));
            response.setStatus(new StatusResponse(1, StatusResponse.Type.SUCCESS, Objects.isNull(bidEntry) ? 0 : 1));
        } catch (Exception e) {
            response.setStatus(new StatusResponse(0, e.getMessage(), StatusResponse.Type.ERROR));
        }
        return response;
    }

    @Override
    public BidResponse getBidsByProduct(Long productId) {
        BidResponse response = new BidResponse();

        try {
            List<BidEntry> bidEntries = bidManager.getBidsForProduct(productId);
            response.setData(bidEntries);
            response.setStatus(new StatusResponse(1, StatusResponse.Type.SUCCESS, Objects.isNull(bidEntries) ? 0 : 1));
        } catch (Exception e) {
            response.setStatus(new StatusResponse(0, e.getMessage(), StatusResponse.Type.ERROR));
        }
        return response;
    }

    @Override
    public UserResponse determineWinner(Long productId) {
        UserResponse response = new UserResponse();
        try {
            UserEntry entry = bidManager.determineWinner(productId);
            response.setData(Collections.singletonList(entry));
            response.setStatus(new StatusResponse(1, StatusResponse.Type.SUCCESS, Objects.isNull(entry) ? 0 : 1));
        } catch (Exception e) {
            response.setStatus(new StatusResponse(0, e.getMessage(), StatusResponse.Type.ERROR));
        }
        return response;
    }

}
