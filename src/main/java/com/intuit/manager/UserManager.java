package com.intuit.manager;

import com.intuit.entry.UserEntry;

public interface UserManager {

    UserEntry getUserById(Long userId);

    UserEntry addUser(UserEntry user) throws Exception;

}
