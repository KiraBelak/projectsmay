package com.finalproject.gamestop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.gamestop.dto.ReviewDTO;
import com.finalproject.gamestop.service.ReviewService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<ReviewDTO>> getAllReviews() {
        List<ReviewDTO> reviews = reviewService.getAllReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable Long id) {
        ReviewDTO review = reviewService.getReviewById(id);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ReviewDTO> createReview(@RequestBody ReviewDTO reviewDTO, Authentication authentication) {
        if (reviewDTO.getUserName() == null || reviewDTO.getUserName().isBlank()) {
            reviewDTO.setUserName(authentication.getName()); // solo si no lo mandaron
        }
        ReviewDTO newReview = reviewService.createReview(reviewDTO);
        return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewDTO> updateReview(@PathVariable Long id, @RequestBody ReviewDTO reviewDTO) {
        ReviewDTO updatedReview = reviewService.updateReview(id, reviewDTO);
        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Review deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ReviewDTO>> getReviewsByProductId(@PathVariable Long productId) {
        List<ReviewDTO> reviews = reviewService.findByProductId(productId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/product/{productId}/rating")
    public ResponseEntity<List<ReviewDTO>> getReviewsByProductIdAndMinRating(
            @PathVariable Long productId,
            @RequestParam int minRating) {
        List<ReviewDTO> reviews = reviewService.findByProductIdAndRatingGreaterThanEqual(productId, minRating);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/user/{userName}")
    public ResponseEntity<List<ReviewDTO>> getReviewsByUserName(@PathVariable String userName) {
        List<ReviewDTO> reviews = reviewService.findByUserName(userName);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/product/{productId}/average-rating")
    public ResponseEntity<Double> getAverageRatingForProduct(@PathVariable Long productId) {
        double averageRating = reviewService.calculateAverageRatingForProduct(productId);
        return new ResponseEntity<>(averageRating, HttpStatus.OK);
    }
}
