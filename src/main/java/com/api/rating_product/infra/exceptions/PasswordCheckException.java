package com.api.rating_product.infra.exceptions;

public class PasswordCheckException extends RuntimeException{
    public PasswordCheckException(){

        super("The password need eight characters");
        // exception para a função de checar o tamanho da senha

    }

}
