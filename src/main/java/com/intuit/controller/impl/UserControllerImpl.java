package com.intuit.controller.impl;


import com.intuit.controller.UserController;
import com.intuit.entry.UserEntry;
import com.intuit.exception.EntityNotFoundException;
import com.intuit.manager.UserManager;
import com.intuit.response.StatusResponse;
import com.intuit.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Objects;

@Component
public class UserControllerImpl implements UserController {

    @Autowired
    private UserManager userManager;

    @Override
    public ResponseEntity<UserResponse> addUser(UserEntry userEntry) {
        UserResponse response = new UserResponse();

        try {
            UserEntry entry = userManager.addUser(userEntry);
            response.setData(Collections.singletonList(entry));
            response.setStatus(new StatusResponse(1, StatusResponse.Type.SUCCESS, Objects.isNull(entry) ? 0 : 1));
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            response.setStatus(new StatusResponse(0, "Error adding new user", StatusResponse.Type.ERROR));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Override
    public ResponseEntity<UserResponse> getUserById(Long userId) {
        UserResponse response = new UserResponse();

        try {
            UserEntry userEntry = userManager.getUserById(userId);
            response.setData(Collections.singletonList(userEntry));
            response.setStatus(new StatusResponse(1, StatusResponse.Type.SUCCESS, Objects.isNull(userEntry) ? 0 : 1));
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            response.setStatus(new StatusResponse(0, "User not found", StatusResponse.Type.ERROR));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            response.setStatus(new StatusResponse(0, "An unexpected error occurred", StatusResponse.Type.ERROR));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
