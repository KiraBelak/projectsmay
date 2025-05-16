package com.alldata.mobsell.impl;

import com.alldata.mobsell.model.User;
import com.alldata.mobsell.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public ResponseEntity<User> updateUser(Long id, User newUser) {
        return userRepository.findById(id)
                .map(u -> {
                    u.setUsername(newUser.getUsername());
                    u.setEmail(newUser.getEmail());
                    userRepository.save(u);
                    return ResponseEntity.ok(u);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<String> deleteUser(Long id) {
        return userRepository.findById(id)
                .map(u -> {
                    userRepository.delete(u);
                    return ResponseEntity.ok("User deleted");
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
