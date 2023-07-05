package com.dinhson.sunshop.securityConfig;

import com.dinhson.sunshop.appUser.Role;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static com.dinhson.sunshop.appUser.Role.*;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig {

    private final CustomAuthenticationSuccessHandler successHandler;

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/home/**",
                                "/css/**",
                                "js/**",
                                "img/**",
                                "fonts/**",
                                "/product/**",
                                "/sign-up/**",
                                "/forget-password/**",
                                "/change-password/**",
                                "/shop/**",
                                "/").permitAll()
                        .requestMatchers("/admin/**").hasAnyAuthority(ADMIN.name(), MANAGER.name())
                        .requestMatchers("/cart/**", "/api/cart/**").hasAuthority(USER.name())
                        .requestMatchers("/user/**").hasAnyAuthority("USER", "ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                        .successHandler(successHandler)
                        .failureUrl("/login?message=Wrong%20UserName%20Or%20Password")
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .permitAll().logoutSuccessUrl("/home")
                )
                .build();
    }

}
