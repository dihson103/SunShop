package com.dinhson.sunshop.appUser.profile;


import com.dinhson.sunshop.appUser.UserDTO;
import com.dinhson.sunshop.appUser.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class UserProfileController {

    private final UserService userService;

    @GetMapping("profile")
    public String getProfile(Model model){
        //TODO get user id
        int userId = 3;
        UserDTO userDTO = userService.getUserDTO(userId);
        model.addAttribute("user", userDTO);
        return "profile";
    }

}
