package com.dinhson.sunshop.appUser.forgetPassword;

import com.dinhson.sunshop.appUser.UserSecurityDTO;
import com.dinhson.sunshop.exception.UserInputWrongForgerPasswordInformation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class ForgetPasswordController {

    private final ForgetPasswordService forgetPasswordService;

    @GetMapping("forget-password")
    public String getPage(){
        return "input-email";
    }

    @PostMapping("forget-password")
    public ModelAndView getForgetPage(@RequestParam(name = "email") String email){
        //TODO: send code
        UserSecurityDTO userDTO = forgetPasswordService.sendUserCode(email);

        ModelAndView modelAndView = new ModelAndView("input-email");
        modelAndView.addObject("message", "Please access email to get change password link!!!");
        return modelAndView;
    }

    @GetMapping("forget-password/change-password")
    public ModelAndView checkForgetToken(@RequestParam(name = "token") String token,
                                         @RequestParam(name = "message", defaultValue = "") String message){
        UserSecurityDTO userDTO = forgetPasswordService.checkToken(token);
        ModelAndView modelAndView = new ModelAndView("forget-password");
        modelAndView.addObject("userDTO", userDTO);
        modelAndView.addObject("token", token);
        modelAndView.addObject("message", message);
        return modelAndView;
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
