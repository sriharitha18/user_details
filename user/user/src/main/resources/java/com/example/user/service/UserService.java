package com.example.user.service;

import com.example.user.model.User;
import com.example.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Create a new user
    public User createUser(User user) {
        user.setPanNum(user.getPanNum().toUpperCase()); // Convert PAN to uppercase
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    // Get a user by ID
    public Optional<User> getUserById(UUID userId) {
        return userRepository.findById(userId);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Update a user
    public Optional<User> updateUser(UUID userId, User updatedUser) {
        Optional<User> existingUser = userRepository.findById(userId);

        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setFullName(updatedUser.getFullName() != null ? updatedUser.getFullName() : user.getFullName());
            user.setMobNum(updatedUser.getMobNum() != null ? updatedUser.getMobNum() : user.getMobNum());
            user.setPanNum(updatedUser.getPanNum() != null ? updatedUser.getPanNum().toUpperCase() : user.getPanNum());
            user.setUpdatedAt(LocalDateTime.now());
            return Optional.of(userRepository.save(user));
        }
        return Optional.empty();
    }

    // Delete a user
    public boolean deleteUser(UUID userId) {
        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }
}
