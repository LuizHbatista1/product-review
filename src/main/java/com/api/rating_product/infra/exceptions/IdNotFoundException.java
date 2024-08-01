package com.api.rating_product.infra.exceptions;

public class IdNotFoundException extends RuntimeException{
    public IdNotFoundException(Long id){

        super("id not found" + id);

    }
}
