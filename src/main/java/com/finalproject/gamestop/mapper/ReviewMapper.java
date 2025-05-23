package com.finalproject.gamestop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.finalproject.gamestop.dto.ReviewDTO;
import com.finalproject.gamestop.model.Review;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    ReviewDTO toDTO(Review review);

    @Mapping(target = "product", ignore = true)
    Review toEntity(ReviewDTO dto);
}
