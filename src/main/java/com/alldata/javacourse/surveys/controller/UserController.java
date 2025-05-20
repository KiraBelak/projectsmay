package com.alldata.javacourse.surveys.controller;

import com.alldata.javacourse.surveys.model.User;
import com.alldata.javacourse.surveys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping
    public void save(@RequestBody User entity) {
        userService.save(entity);
    }

    @GetMapping("/{id}")
    public Optional<User> findById(@PathVariable Integer id) {
        return userService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        userService.deleteById(id);
    }
}
