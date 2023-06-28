package com.dinhson.sunshop.common;

import org.springframework.http.HttpStatus;

public record ApiResponse(
        String message,
        HttpStatus status
) {
}
