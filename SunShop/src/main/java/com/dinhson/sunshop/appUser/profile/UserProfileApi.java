package com.dinhson.sunshop.appUser.profile;

import com.dinhson.sunshop.appUser.UserDTO;
import com.dinhson.sunshop.appUser.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserProfileApi {

    private final UserService userService;

    @PutMapping("profile")
    @ResponseStatus(HttpStatus.OK)
    public String updateProfile(@RequestBody UserDTO userDTO){
        UserDTO user = userService.updateUser(userDTO);
        return "Update profile success!!!";
    }

}