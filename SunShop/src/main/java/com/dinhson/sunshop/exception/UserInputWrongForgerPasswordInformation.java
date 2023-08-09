package com.dinhson.sunshop.exception;

import lombok.Getter;

@Getter
public class UserInputWrongForgerPasswordInformation extends RuntimeException {

    private String token;

    public UserInputWrongForgerPasswordInformation(String message, String token) {
        super(message);
        this.token = token;
    }

}
