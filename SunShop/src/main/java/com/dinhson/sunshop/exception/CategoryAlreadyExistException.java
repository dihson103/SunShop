package com.dinhson.sunshop.exception;

public class CategoryAlreadyExistException extends RuntimeException {

    public CategoryAlreadyExistException(String message) {
        super(message);
    }
}
