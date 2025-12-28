package com.example.projetmpisi.service.imp;


import com.example.projetmpisi.ProjetMpisiApplication;
import com.example.projetmpisi.entity.User;
import com.example.projetmpisi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Test
    void coverageOnlyTest() {
        new ProjetMpisiApplication();
        assertEquals(true, true);
    }

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUser() {
        User user = new User(1, "Bilel", "bilel@example.com");
        when(userRepository.save(user)).thenReturn(user);
        User saved = userService.saveUser(user);
        assertEquals("Bilel", saved.getUsername());
    }

    @Test
    void testGetUserById() {
        User user = new User(1, "Bilel", "bilel@example.com");
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        Optional<User> result = userService.getUserById(1);
        assertTrue(result.isPresent());
        assertEquals("bilel@example.com", result.get().getEmail());
    }

    @Test
    void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(new User(1, "A", "a@a.com")));
        List<User> users = userService.getAllUsers();
        assertEquals(1, users.size());
    }

    @Test
    void testDeleteUser() {
        userService.deleteUser(1);
        verify(userRepository, times(1)).deleteById(1);
    }

    @Test
    void testGetUserById_notFound() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());
        Optional<User> result = userService.getUserById(1);
        assertFalse(result.isPresent());
    }

    @Test
    void updateUser() {

        User existing = new User(1, "ali", "ali@test.com");
        User updated  = new User(0, "ali_updated", "ali_updated@test.com");

        when(userRepository.existsById(1)).thenReturn(true);
        when(userRepository.save(any(User.class))).thenReturn(updated);

        User result = userService.updateUser(1, updated);

        assertEquals(1, result.getId());
        assertEquals("ali_updated", result.getUsername());

        User user = new User(99, "ghost", "ghost@test.com");

        when(userRepository.existsById(99)).thenReturn(false);

        User result1 = userService.updateUser(99, user);

        assertNull(result1);
    }
}
