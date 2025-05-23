package com.course.service;

import com.course.entity.Role;
import com.course.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public Role getByName(String name) {
        return repository.findByName(name).orElseThrow();
    }
}
