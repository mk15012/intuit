package com.example.intuit.controller.impl;


import com.example.intuit.controller.UserController;
import com.example.intuit.entry.UserEntry;
import com.example.intuit.exception.EntityNotFoundException;
import com.example.intuit.manager.UserManager;
import com.example.intuit.response.StatusResponse;
import com.example.intuit.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Objects;

@Component
public class UserControllerImpl implements UserController {

    @Autowired
    private UserManager userManager;

    @Override
    public UserResponse addUser(UserEntry userEntry) {
        UserResponse response = new UserResponse();

        try {
            UserEntry entry = userManager.addUser(userEntry);
            response.setData(Collections.singletonList(entry));
            response.setStatus(new StatusResponse(1, StatusResponse.Type.SUCCESS, Objects.isNull(entry) ? 0 : 1));
        } catch (Exception ex) {
            response.setStatus(new StatusResponse(0, "An unexpected error occurred", StatusResponse.Type.ERROR));
        }
        return response;
    }

    @Override
    public UserResponse getUserById(Long userId) {
        UserResponse response = new UserResponse();

        try {
            UserEntry userEntry = userManager.getUserById(userId);
            response.setData(Collections.singletonList(userEntry));
            response.setStatus(new StatusResponse(1, StatusResponse.Type.SUCCESS, Objects.isNull(userEntry) ? 0 : 1));
        } catch (EntityNotFoundException e) {
            response.setStatus(new StatusResponse(0, "User not found", StatusResponse.Type.ERROR));
        } catch (Exception e) {
            response.setStatus(new StatusResponse(0, "An unexpected error occurred", StatusResponse.Type.ERROR));
        }

        return response;
    }

}
