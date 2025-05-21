package com.alldata.javacourse.surveys.repository;

import com.alldata.javacourse.surveys.model.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends ListCrudRepository<User, Integer> {
    Optional<User> findByName(String name);
}
