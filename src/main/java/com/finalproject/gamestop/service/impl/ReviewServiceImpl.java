package com.finalproject.gamestop.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalproject.gamestop.dto.ReviewDTO;
import com.finalproject.gamestop.exception.ResourceNotFoundException;
import com.finalproject.gamestop.mapper.ReviewMapper;
import com.finalproject.gamestop.model.Product;
import com.finalproject.gamestop.model.Review;
import com.finalproject.gamestop.repository.ProductRepository;
import com.finalproject.gamestop.repository.ReviewRepository;
import com.finalproject.gamestop.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ProductRepository<Product> productRepository;

    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public List<ReviewDTO> getAllReviews() {
        return reviewRepository.findAll().stream()
                .map(reviewMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReviewDTO getReviewById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id: " + id));
        return reviewMapper.toDTO(review);
    }

    @Override
    public ReviewDTO createReview(ReviewDTO reviewDTO) {
        Review review = reviewMapper.toEntity(reviewDTO);
        review.setReviewDate(LocalDateTime.now());

        Product product = productRepository.findById(reviewDTO.getProductId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Product not found with id: " + reviewDTO.getProductId()));

        review.setProduct(product);

        Review savedReview = reviewRepository.save(review);

        updateProductRating(product.getId());

        return reviewMapper.toDTO(savedReview);
    }

    @Override
    public ReviewDTO updateReview(Long id, ReviewDTO reviewDTO) {
        Review existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id: " + id));

        if (reviewDTO.getRating() != 0) {
            existingReview.setRating(reviewDTO.getRating());
        }

        if (reviewDTO.getComment() != null) {
            existingReview.setComment(reviewDTO.getComment());
        }

        if (reviewDTO.getUserName() != null) {
            existingReview.setUserName(reviewDTO.getUserName());
        }

        if (reviewDTO.getProductId() != null &&
                !reviewDTO.getProductId().equals(existingReview.getProduct().getId())) {

            Product newProduct = productRepository.findById(reviewDTO.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Product not found with id: " + reviewDTO.getProductId()));

            Long oldProductId = existingReview.getProduct().getId();
            existingReview.setProduct(newProduct);
            updateProductRating(oldProductId);
        }

        Review updatedReview = reviewRepository.save(existingReview);

        updateProductRating(updatedReview.getProduct().getId());

        return reviewMapper.toDTO(updatedReview);
    }

    @Override
    public void deleteReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id: " + id));

        Long productId = review.getProduct().getId();

        reviewRepository.delete(review);

        updateProductRating(productId);
    }

    @Override
    public List<ReviewDTO> findByProductId(Long productId) {
        return reviewRepository.findByProductId(productId).stream()
                .map(reviewMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> findByProductIdAndRatingGreaterThanEqual(Long productId, int rating) {
        return reviewRepository.findByProductIdAndRatingGreaterThanEqual(productId, rating).stream()
                .map(reviewMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> findByUserName(String userName) {
        return reviewRepository.findByUserName(userName).stream()
                .map(reviewMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public double calculateAverageRatingForProduct(Long productId) {
        return reviewRepository.calculateAverageRatingForProduct(productId);
    }

    private void updateProductRating(Long productId) {
        Double avg = reviewRepository.calculateAverageRatingForProduct(productId);
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            product.setRating(avg != null ? avg : 0.0);
            productRepository.save(product);
        }
    }
}
