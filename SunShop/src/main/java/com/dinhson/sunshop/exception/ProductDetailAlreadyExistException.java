package com.dinhson.sunshop.exception;

public class ProductDetailAlreadyExistException extends RuntimeException {

    public ProductDetailAlreadyExistException(String message) {
        super(message);
    }
}
