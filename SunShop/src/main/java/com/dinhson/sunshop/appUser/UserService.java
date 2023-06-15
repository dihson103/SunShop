package com.dinhson.sunshop.appUser;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findUserById(int id) {
        return userRepository.getById(id).
                orElseThrow(() -> new IllegalArgumentException("User not found!!!"));
    }

}
