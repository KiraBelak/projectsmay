package com.finalproject.gamestop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finalproject.gamestop.model.Product;
import com.finalproject.gamestop.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByProduct(Product product);

    List<Review> findByProductAndRatingGreaterThanEqual(Product product, int rating);

    List<Review> findByUserName(String userName);
}
