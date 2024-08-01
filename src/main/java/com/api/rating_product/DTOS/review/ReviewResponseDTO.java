package com.api.rating_product.DTOS.review;

public record ReviewResponseDTO(String name , Double rating , String comment) {

    // DTO para listar as review apenas com informaçoes relevantes

}
