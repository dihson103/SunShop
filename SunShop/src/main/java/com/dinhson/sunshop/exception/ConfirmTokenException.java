package com.dinhson.sunshop.exception;

import lombok.Getter;

@Getter
public class ConfirmTokenException extends RuntimeException{

    private String status = "404";

    public ConfirmTokenException(String message) {
        super(message);
    }
}
