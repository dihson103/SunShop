package com.dinhson.sunshop.appUser.profile;

import com.dinhson.sunshop.appUser.UserDTO;
import com.dinhson.sunshop.appUser.UserService;
import com.dinhson.sunshop.securityConfig.MyUserDetail;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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

    @PutMapping("profile/change-password")
    public ApiResponse changePassword(@RequestBody ProfileSecurityDTO profileSecurityDTO,
                                      @AuthenticationPrincipal MyUserDetail user){
        userService.changePassword(profileSecurityDTO, user);
        return new ApiResponse("Change password success!!!", HttpStatus.OK);
    }

}
