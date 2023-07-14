package com.dinhson.sunshop.appUser;

import lombok.Builder;

import java.time.LocalDate;
import java.util.Date;

@Builder
public record UserDTO(
        Integer userId,
        String name,
        String email,
        LocalDate dob,
        Boolean gender,
        Role role,
        String password,
        String image,
        Boolean isActive
) {
}
