package com.finalproject.gamestop.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finalproject.gamestop.model.Order;
import com.finalproject.gamestop.model.OrderStatus;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomerEmail(String email);

    List<Order> findByStatus(OrderStatus status);

    List<Order> findByOrderDateBetween(LocalDateTime start, LocalDateTime end);
}
