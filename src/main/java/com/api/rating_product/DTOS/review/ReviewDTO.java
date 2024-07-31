package com.api.rating_product.DTOS.review;

public record ReviewDTO (Long productId , Long userId , Double rating , String comment){
}
