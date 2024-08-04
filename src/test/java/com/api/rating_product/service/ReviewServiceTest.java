package com.api.rating_product.service;

import com.api.rating_product.DTOS.review.ReviewDTO;
import com.api.rating_product.common.ReviewConstant;
import com.api.rating_product.domain.product.Product;
import com.api.rating_product.domain.review.Review;
import com.api.rating_product.domain.user.User;
import com.api.rating_product.repositories.ProductRepository;
import com.api.rating_product.repositories.ReviewRepository;
import com.api.rating_product.repositories.UserRepository;
import com.api.rating_product.service.auth.AuthService;
import com.api.rating_product.service.product.ProductServiceImpl;
import com.api.rating_product.service.rabbitmq.RabbitMqService;
import com.api.rating_product.service.review.ReviewServiceImpl;
import com.api.rating_product.service.user.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ReviewServiceImpl.class)
public class ReviewServiceTest {

    @Autowired
    private ReviewServiceImpl reviewService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserServiceImpl userService;

    @MockBean
    private ProductServiceImpl productService;

    @MockBean
    private RabbitMqService rabbitMqService;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private ReviewRepository reviewRepository;

    @Test
    public void createReview_WithValidDate_ReturnReview(){

        ReviewDTO reviewDTO = ReviewConstant.REVIEW_DTO;
        Review newReview = ReviewConstant.Review;

        Product newProduct = new Product();
        newProduct.setId(1L);

        User newUser = new User();
        newUser.setId(1L);

        Review expectedReview = new Review(reviewDTO, newProduct, newUser);

        when(productService.findProductById(ArgumentMatchers.anyLong())).thenReturn(newProduct);
        when(userService.findUserById(ArgumentMatchers.anyLong())).thenReturn(newUser);
        when(reviewRepository.save(ArgumentMatchers.any(Review.class))).thenReturn(expectedReview);

        Review actualReview = reviewService.createReview(reviewDTO);

        assertThat(actualReview).isEqualTo(expectedReview);

    }

    @Test
    public void createReview_WithInvalidDate_ReturnReview(){

        ReviewDTO reviewDTO = ReviewConstant.REVIEW_DTO;
        Review newReview = ReviewConstant.Review;

        Product newProduct = new Product();
        newProduct.setId(1L);

        User newUser = new User();
        newUser.setId(1L);

        when(reviewRepository.save(newReview)).thenThrow(RuntimeException.class);

        assertThatThrownBy(()->reviewService.createReview(reviewDTO)).isInstanceOf(RuntimeException.class);

    }




}
