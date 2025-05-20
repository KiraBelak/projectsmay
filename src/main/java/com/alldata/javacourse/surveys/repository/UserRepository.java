package com.alldata.javacourse.surveys.repository;

import com.alldata.javacourse.surveys.model.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ListCrudRepository<User, Integer> {
}
