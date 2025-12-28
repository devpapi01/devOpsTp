package com.example.projetmpisi.controller;

import com.example.projetmpisi.entity.User;
import com.example.projetmpisi.service.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserControllerTest {

    private UserController controller;

    @BeforeEach
    void setUp() {
        controller = new UserController(new FakeUserService());
    }

    @Test
    void createUser() {
        User user = new User(3, "amina", "amina@test.com");

        User result = controller.createUser(user).getBody();

        assertEquals("amina", result.getUsername());
    }

    @Test
    void getAllUsers() {
        List<User> users = controller.getAllUsers().getBody();

        assertEquals(2, users.size());
    }

    @Test
    void getUserById() {
        User user = controller.getUserById(1).getBody();

        assertEquals("ali", user.getUsername());
    }

    @Test
    void updateUser() {
        User updated = new User(1, "ali_updated", "ali@test.com");

        User result = controller.updateUser(1, updated).getBody();

        assertEquals("ali_updated", result.getUsername());
    }

    @Test
    void deleteUser() {
        controller.deleteUser(1);

        List<User> users = controller.getAllUsers().getBody();

        assertEquals(1, users.size());
    }


    static class FakeUserService implements IUserService {

        private final List<User> users = new ArrayList<>();

        FakeUserService() {
            users.add(new User(1, "ali", "ali@test.com"));
            users.add(new User(2, "sami", "sami@test.com"));
        }

        @Override
        public User saveUser(User user) {
            users.add(user);
            return user;
        }

        @Override
        public List<User> getAllUsers() {
            return users;
        }

        @Override
        public Optional<User> getUserById(int id) {
            for (User u : users) {
                if (u.getId() == id) {
                    return Optional.of(u);
                }
            }
            return Optional.empty();
        }

        @Override
        public User updateUser(int id, User user) {
            for (User u : users) {
                if (u.getId() == id) {
                    u.setUsername(user.getUsername());
                    u.setEmail(user.getEmail());
                    return u;
                }
            }
            return null;
        }

        @Override
        public void deleteUser(int id) {
            users.removeIf(u -> u.getId() == id);
        }
    }
}
