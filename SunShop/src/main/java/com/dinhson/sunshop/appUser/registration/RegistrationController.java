package com.dinhson.sunshop.appUser.registration;

import com.dinhson.sunshop.appUser.UserSecurityDTO;
import com.dinhson.sunshop.exception.UserNotValidException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("sign-up")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @GetMapping
    public ModelAndView getSignUp(){
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("userDTO", new UserSecurityDTO());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView signUp(@ModelAttribute("userDTO") @Valid UserSecurityDTO userDTO,
                               BindingResult bindingResult){
        if(bindingResult.hasErrors())
            throw new UserNotValidException(bindingResult.getFieldError().getDefaultMessage(), userDTO);

        registrationService.registry(userDTO);

        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("message", "Create account success." +
                " Please check your mail to verify!!!");

        return modelAndView;
    }

    @GetMapping("confirm")
    public ModelAndView confirm(@RequestParam(name = "token") String token){
        registrationService.confirmUser(token);

        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("message", "Verify success, please login to continuous!!!");
        return modelAndView;
    }
}
