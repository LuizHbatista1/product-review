package com.api.rating_product.service.review;

import com.api.rating_product.DTOS.review.ReviewDTO;
import com.api.rating_product.domain.product.Product;
import com.api.rating_product.domain.review.Review;
import com.api.rating_product.domain.user.User;
import com.api.rating_product.repositories.ProductRepository;
import com.api.rating_product.repositories.ReviewRepository;
import com.api.rating_product.repositories.UserRepository;
import com.api.rating_product.service.product.ProductServiceImpl;
import com.api.rating_product.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductServiceImpl productService;
    private final UserServiceImpl userService;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, UserRepository userRepository, ProductRepository productRepository, ProductServiceImpl productService, UserServiceImpl userService) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.productService = productService;
        this.userService = userService;

    }

    @Override
    public Review createReview(ReviewDTO reviewDTO) {
        User user = userService.findUserById(reviewDTO.userId());
        Product product = productService.findProductById(reviewDTO.productId());
        Review newReview = new Review();
        newReview.setProductId(product);
        newReview.setUserId(user);
        newReview.setRating(newReview.getRating());
        newReview.setComment(newReview.getComment());
        this.saveReview(newReview);
        return newReview;

    }

    public void saveReview(Review review){

        this.reviewRepository.save(review);

    }
}
