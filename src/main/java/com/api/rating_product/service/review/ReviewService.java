package com.api.rating_product.service.review;

import com.api.rating_product.DTOS.review.ReviewDTO;
import com.api.rating_product.domain.review.Review;

public interface ReviewService {

    Review createReview(ReviewDTO reviewDTO);

}
