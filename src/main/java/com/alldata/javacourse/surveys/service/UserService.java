package com.alldata.javacourse.surveys.service;

import com.alldata.javacourse.surveys.model.User;
import com.alldata.javacourse.surveys.repository.UserRepository;
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

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findById(Integer integer) {
        return userRepository.findById(integer);
    }

    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    public void deleteById(Integer integer) {
        userRepository.deleteById(integer);
    }
}
