package com.dinhson.sunshop.appUser;

import java.time.LocalDate;
import java.util.Date;

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
    public static UserDTO withUserDTOTest(String name, Boolean isActive, Role role){
        return new UserDTO(
                null,
                name,
                null,
                null,
                null,
                role,
                null,
                null,
                isActive
        );
    }
}
