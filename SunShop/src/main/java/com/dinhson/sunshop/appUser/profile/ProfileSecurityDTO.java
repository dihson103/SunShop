package com.dinhson.sunshop.appUser.profile;

import jakarta.validation.constraints.NotNull;

public record ProfileSecurityDTO(
        String email,
        String password,
        String newPassword,
        String rePassword
) {
}
