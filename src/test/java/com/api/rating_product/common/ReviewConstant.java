package com.api.rating_product.common;

import com.api.rating_product.DTOS.product.ProductDTO;
import com.api.rating_product.DTOS.review.ReviewDTO;
import com.api.rating_product.domain.product.Product;
import com.api.rating_product.domain.review.Review;

public class ReviewConstant {

    public static final ReviewDTO REVIEW_DTO =  new ReviewDTO(1L,1L , 4.5,"Teste");
    public static final ReviewDTO INVALID_REVIEW =  new ReviewDTO(1L,1L , 4.5,"");
    public static final Review Review = new Review(REVIEW_DTO);



}
