package com.api.rating_product.repositories;

import com.api.rating_product.domain.product.Product;
import com.api.rating_product.domain.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findReviewByProductId(Product productId);

}
