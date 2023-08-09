package com.dinhson.sunshop.securityConfig;

import com.dinhson.sunshop.appUser.User;
import com.dinhson.sunshop.appUser.UserRepository;
import com.dinhson.sunshop.exception.UsersNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByEmailAndActiveIsTrue(email);
        User u = user.orElseThrow(() -> new UsersNotFoundException("Could not find user!!!"));
        return new MyUserDetail(u);
    }
}
