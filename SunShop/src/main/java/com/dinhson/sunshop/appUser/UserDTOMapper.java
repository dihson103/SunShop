package com.dinhson.sunshop.appUser;

import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserDTOMapper implements Function<User, UserDTO> {
    @Override
    public UserDTO apply(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getDob(),
                user.getGender(),
                user.getRole(),
                null,
                user.getImage(),
                user.getIsActive()
        );
    }
}
