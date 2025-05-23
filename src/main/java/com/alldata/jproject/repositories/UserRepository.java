package com.alldata.jproject.repositories;

import com.alldata.jproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    @Query(value = "SELECT id FROM Users WHERE email = :email", nativeQuery = true)
    long findUserId(String email);
}
