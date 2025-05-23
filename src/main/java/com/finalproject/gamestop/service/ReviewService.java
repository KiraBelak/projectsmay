package com.finalproject.gamestop.service;

import java.util.List;

import com.finalproject.gamestop.dto.ReviewDTO;

public interface ReviewService {

    List<ReviewDTO> getAllReviews();

    ReviewDTO getReviewById(Long id);

    ReviewDTO createReview(ReviewDTO reviewDTO);

    ReviewDTO updateReview(Long id, ReviewDTO reviewDTO);

    void deleteReview(Long id);

    List<ReviewDTO> findByProductId(Long productId);

    List<ReviewDTO> findByProductIdAndRatingGreaterThanEqual(Long productId, int rating);

    List<ReviewDTO> findByUserName(String userName);

    double calculateAverageRatingForProduct(Long productId);
}
