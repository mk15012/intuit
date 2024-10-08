package com.intuit.controller;

import com.intuit.entry.UserEntry;
import com.intuit.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public interface UserController {

    @PostMapping("/addUser")
    ResponseEntity<UserResponse> addUser(@RequestBody UserEntry userEntry);

    @GetMapping("/{userId}")
    ResponseEntity<UserResponse> getUserById(@PathVariable Long userId);

}
