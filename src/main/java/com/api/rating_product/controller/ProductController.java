package com.api.rating_product.controller;

import com.api.rating_product.DTOS.product.ProductDTO;
import com.api.rating_product.DTOS.review.ReviewDTO;
import com.api.rating_product.DTOS.review.ReviewResponseDTO;
import com.api.rating_product.domain.product.Product;
import com.api.rating_product.domain.review.Review;
import com.api.rating_product.service.product.ProductServiceImpl;
import com.api.rating_product.service.review.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/product")
    public ResponseEntity<List<Product>>getAllProducts(@RequestBody ProductDTO productDTO){

        List<Product> products = productService.findAllProducts();
        return new ResponseEntity<>(products , HttpStatus.OK);

    }

    @GetMapping("/{productId}/review")
    public ResponseEntity<List<ReviewResponseDTO>>getReviewByProduct(@PathVariable Long productId){

        List<Review> reviews = reviewService.findReviewByProductId(productId);

        List<ReviewResponseDTO> responseDTOS = reviews.stream().map(review -> {

            String username = review.getUserId().getFirstName();
            String comment = review.getComment();
            Double rating = review.getRating();
            return new ReviewResponseDTO(username , rating , comment);

        }).collect(Collectors.toList());

        return new ResponseEntity<>(responseDTOS , HttpStatus.OK);

    }

}
