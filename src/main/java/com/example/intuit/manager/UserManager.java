package com.example.intuit.manager;

import com.example.intuit.entry.UserEntry;

public interface UserManager {

    UserEntry getUserById(Long userId);

    UserEntry addUser(UserEntry user);

}
