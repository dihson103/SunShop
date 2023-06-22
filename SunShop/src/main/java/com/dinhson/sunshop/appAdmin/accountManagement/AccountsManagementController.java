package com.dinhson.sunshop.appAdmin.accountManagement;

import com.dinhson.sunshop.appUser.UserDTO;
import com.dinhson.sunshop.appUser.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class AccountsManagementController {

    private final UserService userService;

    @GetMapping("admin/users")
    public String getUsers(@RequestParam(required = false, defaultValue = "") String name,
                           @RequestParam(required = false, defaultValue = "all") String role,
                           @RequestParam(required = false, defaultValue = "all") String isActive,
                           Model model){
        List<UserDTO> userDTOS = userService.searchUsers(isActive, role, name);
        model.addAttribute("users", userDTOS);
        model.addAttribute("role", role);
        model.addAttribute("isActive", isActive);
        model.addAttribute("search", name);
        return "admin-accounts-management";
    }
}
