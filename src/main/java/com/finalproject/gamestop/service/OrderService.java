package com.finalproject.gamestop.service;

import java.time.LocalDateTime;
import java.util.List;

import com.finalproject.gamestop.dto.OrderDTO;
import com.finalproject.gamestop.model.OrderStatus;

public interface OrderService {

    List<OrderDTO> getAllOrders();

    OrderDTO getOrderById(Long id);

    OrderDTO createOrder(OrderDTO orderDTO);

    OrderDTO updateOrder(Long id, OrderDTO orderDTO);

    OrderDTO updateOrderStatus(Long id, OrderStatus status);

    void cancelOrder(Long id);

    List<OrderDTO> findByCustomerEmail(String email);

    List<OrderDTO> findByStatus(OrderStatus status);

    List<OrderDTO> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
