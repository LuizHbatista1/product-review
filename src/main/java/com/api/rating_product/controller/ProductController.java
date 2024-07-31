package com.api.rating_product.controller;

import com.api.rating_product.DTOS.product.ProductDTO;
import com.api.rating_product.DTOS.review.ReviewDTO;
import com.api.rating_product.domain.product.Product;
import com.api.rating_product.domain.review.Review;
import com.api.rating_product.service.product.ProductServiceImpl;
import com.api.rating_product.service.review.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ProductController {

    private ProductServiceImpl productService;
    private final ReviewServiceImpl reviewService;

    @Autowired
    public ProductController(ReviewServiceImpl reviewService , ProductServiceImpl productService) {
        this.reviewService = reviewService;
        this.productService = productService;
    }


    @PostMapping("/product")
    public ResponseEntity<Product>createProduct(@RequestBody ProductDTO productDTO){

        Product newProduct = productService.createProduct(productDTO);
        return new ResponseEntity<>(newProduct , HttpStatus.CREATED);

    }

    @PostMapping("/review")
    public ResponseEntity<Review>createReview(@RequestBody ReviewDTO reviewDTO){

        Review newReview = reviewService.createReview(reviewDTO);
        return new ResponseEntity<>(newReview , HttpStatus.CREATED);

    }



}
