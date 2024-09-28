package com.example.intuit.controller;

import com.example.intuit.entry.BidEntry;
import com.example.intuit.response.BidResponse;
import com.example.intuit.response.UserResponse;
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
    BidResponse getBidById(@PathVariable Long bidId);

    @PostMapping("/placeBid")
    BidResponse placeBid(@RequestBody BidEntry bidEntry) throws Exception;

    @GetMapping("/getBidsByProduct/{productId}")
    BidResponse getBidsByProduct(@PathVariable Long productId);

    @GetMapping("/winner/{productId}")
    UserResponse determineWinner(@PathVariable Long productId);

}