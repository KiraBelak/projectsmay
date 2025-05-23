package com.finalproject.gamestop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.finalproject.gamestop.dto.OrderDTO;
import com.finalproject.gamestop.dto.OrderDTO.OrderItemDTO;
import com.finalproject.gamestop.model.Order;
import com.finalproject.gamestop.model.OrderItem;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDTO toDTO(Order order);

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    OrderItemDTO orderItemToDTO(OrderItem orderItem);

    @Mapping(target = "items", ignore = true)
    Order toEntity(OrderDTO dto);
}
