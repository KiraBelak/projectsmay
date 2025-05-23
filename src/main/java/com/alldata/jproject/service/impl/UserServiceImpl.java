package com.alldata.jproject.service.impl;

import com.alldata.jproject.entities.User;
import com.alldata.jproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User registerNewUser(User user){return userRepository.save(user);}

    public Optional<User> getUserById(Long id){return userRepository.findById(id);}

    public long findUserId(String email){return userRepository.findUserId(email);}

}
