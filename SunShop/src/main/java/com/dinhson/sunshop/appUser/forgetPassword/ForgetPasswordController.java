package com.dinhson.sunshop.appUser.forgetPassword;

import com.dinhson.sunshop.appUser.UserSecurityDTO;
import com.dinhson.sunshop.appUser.UserService;
import com.dinhson.sunshop.exception.UserInputWrongForgerPasswordInformation;
import com.dinhson.sunshop.securityConfig.MyUserDetail;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class ForgetPasswordController {

    private final ForgetPasswordService forgetPasswordService;
    private final UserService userService;

    @GetMapping("forget-password")
    public String getPage(@AuthenticationPrincipal MyUserDetail user){
        String access = userService.checkAuthenticated(user);
        if(access != null){
            return access;
        }
        return "input-email";
    }

    @PostMapping("forget-password")
    public String getForgetPage(@RequestParam(name = "email") String email,
                                      Model model){

        //TODO: send code
        UserSecurityDTO userDTO = forgetPasswordService.sendUserCode(email);

        model.addAttribute("message", "Please access email to get change password link!!!");
        return "input-email";
    }

    @GetMapping("forget-password/change-password")
    public String checkForgetToken(@RequestParam(name = "token") String token,
                                         @RequestParam(name = "message", defaultValue = "") String message,
                                         @AuthenticationPrincipal MyUserDetail user,
                                         Model model){

        String access = userService.checkAuthenticated(user);
        if(access != null){
            return access;
        }

        UserSecurityDTO userDTO = forgetPasswordService.checkToken(token);
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("userDTO", userDTO);
        model.addAttribute("token", token);
        model.addAttribute("message", message);
        return "forget-password";
    }

    @PostMapping("change-password")
    public ModelAndView updatePassword(@ModelAttribute(name = "userDTO") @Valid UserSecurityDTO userDTO,
                                       @RequestParam(name = "token") String token,
                                       BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new UserInputWrongForgerPasswordInformation(bindingResult.getFieldError().getDefaultMessage(), token);
        }

        forgetPasswordService.updatePassword(userDTO, token);

        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("message", "Password was changed!!!");
        return modelAndView;
    }
}
