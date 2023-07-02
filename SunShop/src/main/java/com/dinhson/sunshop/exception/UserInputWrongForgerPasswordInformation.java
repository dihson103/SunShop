package com.dinhson.sunshop.exception;

public class UserInputWrongForgerPasswordInformation extends RuntimeException {

    private String token;

    public UserInputWrongForgerPasswordInformation(String message, String token) {
        super(message);
        this.token = token;
    }

}
