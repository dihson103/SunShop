package com.dinhson.sunshop.securityConfig;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        MyUserDetail myUserDetail = (MyUserDetail) authentication.getPrincipal();

        switch (myUserDetail.getRole()){
            case USER:
                response.sendRedirect("/home");
                break;
            case ADMIN, MANAGER:
                response.sendRedirect("/admin/users");
                break;
        }

    }
}
