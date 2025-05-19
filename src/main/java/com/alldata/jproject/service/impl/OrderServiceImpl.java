package com.alldata.jproject.service.impl;

import com.alldata.jproject.entities.Order;
import com.alldata.jproject.entities.User;
import com.alldata.jproject.repositories.OrderRepository;
import com.alldata.jproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl {
    //en service impl se hace la logica de consulta
    private final UserRepository userRepository;

    @Autowired
    public OrderServiceImpl( UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<Order> listUserOrders(Long userId){
        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("Error, user not found"));

        return user.getOrders();
    }

    public User storeOrder(Long userId, Order order){
        //se hace una consulta para extraer el usuario para luego setearlo en la clase order recibida en los parametros
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Error, user not found"));

        order.setUser(user);
        user.getOrders().add(order);
        return userRepository.save(user);
    }
}
