package com.dinhson.sunshop.exception;

import com.dinhson.sunshop.appUser.UserSecurityDTO;

public class UserNotValidException extends RuntimeException{

    private UserSecurityDTO userSecurityDTO;

//    //public UserNotValidException(String message) {
//        super(message);
//    }

    public UserNotValidException(String message, UserSecurityDTO userSecurityDTO) {
        super(message);
        this.userSecurityDTO = userSecurityDTO;
    }

}
