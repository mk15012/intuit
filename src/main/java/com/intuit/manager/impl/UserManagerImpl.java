package com.intuit.manager.impl;

import com.intuit.entities.User;
import com.intuit.entry.UserEntry;
import com.intuit.exception.EntityNotFoundException;
import com.intuit.manager.UserManager;
import com.intuit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserManagerImpl implements UserManager {

    @Autowired
    private UserRepository userRepository;

    public UserEntry getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        return convertToEntry(user);
    }

    public UserEntry addUser(UserEntry userEntry) {
        userRepository.save(convertToEntity(userEntry));
        return userEntry;
    }

    private User convertToEntity(UserEntry userEntry) {
        User user = new User();
        user.setId(userEntry.getId());
        user.setName(userEntry.getName());
        user.setUserType(userEntry.getUserType().name());
        return user;
    }

    private UserEntry convertToEntry(User user) {
        return UserEntry.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

}
