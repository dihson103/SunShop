package com.dinhson.sunshop.exception;

import com.dinhson.sunshop.appUser.UserSecurityDTO;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler({UsersNotFoundException.class})
    public String handleUserNotFoundException(UsersNotFoundException exception,
                                              Model model){
        model.addAttribute("message", exception.getMessage());
        return "login";
    }

    @ExceptionHandler(UserNotValidException.class)
    public String handleUserNotValidException(UserNotValidException exception,
                                              Model model){
        model.addAttribute("message", exception.getMessage());
        model.addAttribute("userDTO", new UserSecurityDTO());
        return "register";
    }

    @ExceptionHandler(UserInputWrongForgerPasswordInformation.class)
    public String handleUserInputWrongForgerPasswordInformation (UserInputWrongForgerPasswordInformation exception){
        return "redirect:/forget-password/change-password?token=" + exception.getToken()
                + "&&message="+exception.getMessage();
    }

    @ExceptionHandler(SizeAlreadyExistException.class)
    public String handleSizeAlreadyExistException(SizeAlreadyExistException exception,
                                                  RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message", exception.getMessage());
        return "redirect:/admin/sizes";
    }

    @ExceptionHandler(ForgetPasswordEmailNotTrueException.class)
    public String handleForgetPasswordEmailNotTrueException(ForgetPasswordEmailNotTrueException exception,
                                                            Model model){
        model.addAttribute("message", exception.getMessage());
        return "input-email";
    }

    @ExceptionHandler(ConfirmTokenException.class)
    public String handleConfirmTokenException(ConfirmTokenException exception, Model model){
        model.addAttribute("status", exception.getStatus());
        model.addAttribute("message", exception.getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleOtherException (Exception exception, Model model){
        model.addAttribute("status", 500);
        model.addAttribute("message", "Sorry, there are something error!");
        return "error";
    }


}
