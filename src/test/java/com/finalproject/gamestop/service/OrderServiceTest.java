package com.finalproject.gamestop.service;

import com.finalproject.gamestop.dto.OrderDTO;
import com.finalproject.gamestop.mapper.OrderMapper;
import com.finalproject.gamestop.model.Order;
import com.finalproject.gamestop.model.OrderStatus;
import com.finalproject.gamestop.repository.OrderRepository;
import com.finalproject.gamestop.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllOrders() {
        Order order = new Order();
        order.setId(1L);
        when(orderRepository.findAll()).thenReturn(Collections.singletonList(order));
        when(orderMapper.toDTO(any())).thenReturn(new OrderDTO());

        assertEquals(1, orderService.getAllOrders().size());
    }

    @Test
    void testGetOrderById() {
        Order order = new Order();
        order.setId(1L);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(orderMapper.toDTO(any())).thenReturn(new OrderDTO());

        OrderDTO dto = orderService.getOrderById(1L);
        assertNotNull(dto);
    }

    @Test
    void testFindByStatus() {
        Order order = new Order();
        order.setId(1L);
        order.setStatus(OrderStatus.SHIPPED);
        when(orderRepository.findByStatus(OrderStatus.SHIPPED)).thenReturn(Collections.singletonList(order));
        when(orderMapper.toDTO(any())).thenReturn(new OrderDTO());

        assertEquals(1, orderService.findByStatus(OrderStatus.SHIPPED).size());
    }

    @Test
    void testFindByOrderDateBetween() {
        Order order = new Order();
        order.setId(1L);
        LocalDateTime now = LocalDateTime.now();
        when(orderRepository.findByOrderDateBetween(any(), any())).thenReturn(Collections.singletonList(order));
        when(orderMapper.toDTO(any())).thenReturn(new OrderDTO());

        assertEquals(1, orderService.findByOrderDateBetween(now.minusDays(1), now.plusDays(1)).size());
    }
}
