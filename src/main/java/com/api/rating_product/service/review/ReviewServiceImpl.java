package com.api.rating_product.service.review;

import com.api.rating_product.DTOS.review.ReviewDTO;
import com.api.rating_product.domain.product.Product;
import com.api.rating_product.domain.review.Review;
import com.api.rating_product.domain.user.User;
import com.api.rating_product.repositories.ProductRepository;
import com.api.rating_product.repositories.ReviewRepository;
import com.api.rating_product.repositories.UserRepository;
import com.api.rating_product.service.product.ProductServiceImpl;
import com.api.rating_product.service.rabbitmq.RabbitMqService;
import com.api.rating_product.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductServiceImpl productService;
    private final UserServiceImpl userService;
    private final RabbitMqService rabbitMqService;
    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, UserRepository userRepository, ProductRepository productRepository, ProductServiceImpl productService, UserServiceImpl userService, RabbitMqService rabbitMqService) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.productService = productService;
        this.userService = userService;
        this.rabbitMqService = rabbitMqService;
    }

    @Override
    public Review createReview(ReviewDTO reviewDTO) {
        User user = userService.findUserById(reviewDTO.userId());
        Product product = productService.findProductById(reviewDTO.productId());
        Review newReview = new Review(reviewDTO);
        newReview.setProductId(product);
        newReview.setUserId(user);
        newReview.setRating(newReview.getRating());
        newReview.setComment(newReview.getComment());
        this.saveReview(newReview);
        rabbitMqService.NotificationQueue(exchange , newReview); // notifica a queue do rabbit
        return newReview;

    }

    public void saveReview(Review review){

        this.reviewRepository.save(review);

    }

    @Override
    public List<Review> findReviewByProductId(Long productId) {

        Product product =  productRepository.findById(productId).orElseThrow(()-> new RuntimeException());
        return reviewRepository.findReviewByProductId(product); // busca a review pelo id do produto

    }

}
