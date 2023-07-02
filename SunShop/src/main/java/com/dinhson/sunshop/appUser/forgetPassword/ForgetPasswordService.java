package com.dinhson.sunshop.appUser.forgetPassword;


import com.dinhson.sunshop.appUser.User;
import com.dinhson.sunshop.appUser.UserSecurityDTO;
import com.dinhson.sunshop.appUser.UserService;
import com.dinhson.sunshop.confirmToken.ConfirmToken;
import com.dinhson.sunshop.confirmToken.ConfirmTokenService;
import com.dinhson.sunshop.exception.UserInputWrongForgerPasswordInformation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ForgetPasswordService {

    private final UserService userService;

    private final ConfirmTokenService confirmTokenService;

    public UserSecurityDTO sendUserCode(String email) {
        User user = userService.getUserByEmail(email);

        String token = confirmTokenService.createConfirmToken(user);
        String link = "http://localhost:8080/forget-password/change-password?token=" + token;

        System.out.println("Link: " + link);

        //TODO: send email

        return new UserSecurityDTO(user);
    }

    public void updatePassword(UserSecurityDTO userDTO, String token) {

        if(!userDTO.isEqualPassword())
            throw new UserInputWrongForgerPasswordInformation("Password and re-password must be the same!!!", token);

        //TODO: get the number of times the user entered

        ConfirmToken confirmToken = confirmTokenService.confirm(token);
        confirmTokenService.updateConfirmDate(confirmToken);

        userService.changeUserPassword(userDTO);
    }

    public UserSecurityDTO checkToken(String token) {
        User user = confirmTokenService.checkForgetPasswordToken(token);
        return new UserSecurityDTO(user);
    }
}
