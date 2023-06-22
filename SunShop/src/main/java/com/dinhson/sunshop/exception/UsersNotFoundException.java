package com.dinhson.sunshop.exception;

public class UsersNotFoundException extends RuntimeException{
    public UsersNotFoundException(String message) {
        super(message);
    }
}
