package com.intuit.manager;

import com.intuit.entities.User;
import com.intuit.entry.UserEntry;
import com.intuit.exception.EntityNotFoundException;
import com.intuit.manager.impl.UserManagerImpl;
import com.intuit.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserManagerImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserManagerImpl underTest;

    @Test
    public void testGetUserById() {
        User user = new User();
        user.setId(1L);
        user.setName("Test User");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserEntry result = underTest.getUserById(1L);
        assertEquals("Test User", result.getName());
    }

    @Test
    public void testGetUserByIdNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> underTest.getUserById(1L));
    }

    private User convertToEntity(UserEntry userEntry) {
        User user = new User();
        user.setId(userEntry.getId());
        user.setName(userEntry.getName());
        return user;
    }
}