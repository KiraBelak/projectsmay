package com.finalproject.gamestop.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finalproject.gamestop.dto.OrderDTO;
import com.finalproject.gamestop.dto.OrderDTO.OrderItemDTO;
import com.finalproject.gamestop.exception.ResourceNotFoundException;
import com.finalproject.gamestop.mapper.OrderMapper;
import com.finalproject.gamestop.model.Order;
import com.finalproject.gamestop.model.OrderItem;
import com.finalproject.gamestop.model.OrderStatus;
import com.finalproject.gamestop.model.Product;
import com.finalproject.gamestop.repository.OrderRepository;
import com.finalproject.gamestop.repository.ProductRepository;
import com.finalproject.gamestop.service.OrderService;
import com.finalproject.gamestop.websocket.OrderNotificationController;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository<Product> productRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderNotificationController orderNotificationController;

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        return orderMapper.toDTO(order);
    }

    @Override
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = orderMapper.toEntity(orderDTO);
        order.setOrderDate(LocalDateTime.now());

        if (order.getStatus() == null) {
            order.setStatus(OrderStatus.PENDING);
        }

        BigDecimal total = BigDecimal.ZERO;

        if (orderDTO.getItems() != null) {
            for (OrderItemDTO itemDTO : orderDTO.getItems()) {
                OrderItem item = new OrderItem();

                Product product = productRepository.findById(itemDTO.getProductId())
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Product not found with id: " + itemDTO.getProductId()));

                BigDecimal price = BigDecimal.valueOf(product.getPrice());
                int quantity = itemDTO.getQuantity();
                BigDecimal subtotal = price.multiply(BigDecimal.valueOf(quantity));

                item.setProduct(product);
                item.setQuantity(quantity);
                item.setUnitPrice(price);
                item.setSubtotal(subtotal);

                order.addItem(item);

                total = total.add(subtotal);
            }
        }

        order.setTotalAmount(total);

        Order savedOrder = orderRepository.save(order);
        orderNotificationController.notifyOrderStatusChanged(orderMapper.toDTO(savedOrder));
        return orderMapper.toDTO(savedOrder);
    }

    @Override
    @Transactional
    public OrderDTO updateOrder(Long id, OrderDTO orderDTO) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));

        if (orderDTO.getCustomerName() != null)
            existingOrder.setCustomerName(orderDTO.getCustomerName());
        if (orderDTO.getCustomerEmail() != null)
            existingOrder.setCustomerEmail(orderDTO.getCustomerEmail());
        if (orderDTO.getShippingAddress() != null)
            existingOrder.setShippingAddress(orderDTO.getShippingAddress());
        if (orderDTO.getStatus() != null)
            existingOrder.setStatus(orderDTO.getStatus());

        if (orderDTO.getItems() != null) {
            existingOrder.getItems().clear();

            for (OrderItemDTO itemDTO : orderDTO.getItems()) {
                OrderItem item = new OrderItem();
                Product product = productRepository.findById(itemDTO.getProductId())
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Product not found with id: " + itemDTO.getProductId()));
                BigDecimal price = BigDecimal.valueOf(product.getPrice());
                int quantity = itemDTO.getQuantity();
                BigDecimal subtotal = price.multiply(BigDecimal.valueOf(quantity));

                item.setProduct(product);
                item.setQuantity(quantity);
                item.setUnitPrice(price);
                item.setSubtotal(subtotal);
                item.setOrder(existingOrder);

                existingOrder.getItems().add(item);
            }

            BigDecimal total = existingOrder.getItems().stream()
                    .map(OrderItem::getSubtotal)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            existingOrder.setTotalAmount(total);
        }

        Order updatedOrder = orderRepository.save(existingOrder);
        orderNotificationController.notifyOrderStatusChanged(orderMapper.toDTO(updatedOrder));
        return orderMapper.toDTO(updatedOrder);
    }

    @Override
    public OrderDTO updateOrderStatus(Long id, OrderStatus status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        order.setStatus(status);
        Order updatedOrder = orderRepository.save(order);
        orderNotificationController.notifyOrderStatusChanged(orderMapper.toDTO(updatedOrder));
        return orderMapper.toDTO(updatedOrder);
    }

    @Override
    public void cancelOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        OrderDTO dto = orderMapper.toDTO(order);
        dto.setStatus(OrderStatus.CANCELED);
        orderNotificationController.notifyOrderStatusChanged(dto);
        orderRepository.delete(order);
    }

    @Override
    public List<OrderDTO> findByCustomerEmail(String email) {
        return orderRepository.findByCustomerEmail(email).stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> findByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status).stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return orderRepository.findByOrderDateBetween(startDate, endDate).stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }
}
