package com.alldata.jproject.repositories;

import com.alldata.jproject.entities.Order;
import com.alldata.jproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//se tiene que pasar como parametro en jpa la clase del repositorio, en este caso order
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
//    Optional<User> findByEmail(String email);

    @Query("SELECT email FROM User WHERE email = :email")
    User findByEmail(@Param("email") String email);

}
