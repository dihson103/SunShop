package com.dinhson.sunshop.appUser;

import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserDTOMapper implements Function<User, UserDTO> {
    @Override
    public UserDTO apply(User user) {
        return UserDTO
                .builder()
                .userId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .dob(user.getDob())
                .gender(user.getGender())
                .role(user.getRole())
                .image(user.getImage())
                .isActive(user.getIsActive())
                .build();
    }
}
