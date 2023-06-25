package com.dinhson.sunshop.appUser.profile;

import org.springframework.http.HttpStatus;

public record ApiResponse(
        String message,
        HttpStatus status
) {
}
