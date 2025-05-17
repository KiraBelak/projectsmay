package com.alldata.jproject.controller;

import com.alldata.jproject.entities.Order;
import com.alldata.jproject.entities.User;
import com.alldata.jproject.repositories.UserRepository;
import com.alldata.jproject.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/order")
public class OrderController {
    private final OrderServiceImpl orderService;
    private final UserRepository userRepository;

    @Autowired
    public OrderController(OrderServiceImpl orderService, UserRepository userRepository){
        this.orderService = orderService;
        this.userRepository = userRepository;
    }

    @PostMapping("{userId}")
    public ResponseEntity<User> addNewOrder(@PathVariable Long userId, @RequestBody Order order){
        return ResponseEntity.ok(orderService.storeOrder(userId, order));
    }

    @GetMapping("{userId}")
    public ResponseEntity<List<Order>> listAllOrders(@PathVariable Long userId){
        return ResponseEntity.ok(orderService.listUserOrders(userId));
    }
}
