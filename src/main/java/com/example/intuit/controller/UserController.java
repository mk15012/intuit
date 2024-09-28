package com.example.intuit.controller;

import com.example.intuit.entry.UserEntry;
import com.example.intuit.response.UserResponse;
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
    UserResponse addUser(@RequestBody UserEntry userEntry);

    @GetMapping("/{userId}")
    UserResponse getUserById(@PathVariable Long userId);

}
