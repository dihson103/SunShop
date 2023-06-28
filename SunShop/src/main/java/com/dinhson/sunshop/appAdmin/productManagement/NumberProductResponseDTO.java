package com.dinhson.sunshop.appAdmin.productManagement;

import org.springframework.http.HttpStatus;

public record NumberProductResponseDTO(
        String message,
        int number,
        HttpStatus status
) {
}
