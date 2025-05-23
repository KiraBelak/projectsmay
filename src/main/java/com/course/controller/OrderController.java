package com.course.controller;

import com.course.dto.OrderDto;
import com.course.entity.Order;
import com.course.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/private/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getOrderHistory() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @PostMapping
    @MessageMapping("/newOrder")
    @SendTo("/topic/ordenes")
    public OrderDto nuevaOrden(@RequestBody OrderDto order) {
        // Guardar la orden en la base de datos antes de enviarla
        return order; // Se envía a todos los clientes suscritos en `/topic/ordenes`
    }

    @MessageMapping("/removeOrder")
    @SendTo("/topic/removeOrder")
    public OrderDto eliminarOrden(@Payload OrderDto orden) {
        return orden; // Enviar la orden eliminada a todos los clientes
    }
}
