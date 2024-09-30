package com.intuit.controller;

import com.intuit.entry.BidEntry;
import com.intuit.response.BidResponse;
import com.intuit.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bids")
public interface BidController {

    @GetMapping("/{bidId}")
    ResponseEntity<BidResponse> getBidById(@PathVariable Long bidId);

    @PostMapping("/placeBid")
    ResponseEntity<BidResponse> placeBid(@RequestBody BidEntry bidEntry) throws Exception;

    @GetMapping("/getBidsByProduct/{productId}")
    ResponseEntity<BidResponse> getBidsByProduct(@PathVariable Long productId);

    @GetMapping("/winner/{productId}")
    ResponseEntity<UserResponse> determineWinner(@PathVariable Long productId);

}