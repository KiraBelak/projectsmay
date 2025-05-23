package com.course.service;

import com.course.entity.AppUser;
import com.course.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AppUser save(AppUser appUser) {
        return userRepository.save(appUser);
    }

    public Optional<AppUser> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public List<AppUser> findAll(){
        return userRepository.findAll();
    }

    public Optional<AppUser> getUserById(int id) {
        return userRepository.findById(id);
    }
}
