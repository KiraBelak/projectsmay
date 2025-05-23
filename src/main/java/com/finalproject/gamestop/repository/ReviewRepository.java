package com.finalproject.gamestop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.finalproject.gamestop.model.Product;
import com.finalproject.gamestop.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByProduct(Product product);

    List<Review> findByProductAndRatingGreaterThanEqual(Product product, int rating);

    List<Review> findByUserName(String userName);

    List<Review> findByProductId(Long productId);

    List<Review> findByProductIdAndRatingGreaterThanEqual(Long productId, int rating);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.product.id = :productId")
    Double calculateAverageRatingForProduct(@Param("productId") Long productId);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.product.id = :productId AND r.rating >= :minRating")
    Double calculateAverageRatingForProductWithMin(@Param("productId") Long productId,
            @Param("minRating") int minRating);
}
