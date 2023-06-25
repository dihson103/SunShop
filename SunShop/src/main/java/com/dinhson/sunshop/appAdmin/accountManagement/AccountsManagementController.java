package com.dinhson.sunshop.appAdmin.accountManagement;

import com.dinhson.sunshop.appUser.User;
import com.dinhson.sunshop.appUser.UserDTO;
import com.dinhson.sunshop.appUser.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("admin/users")
public class AccountsManagementController {

    private final UserService userService;

    @GetMapping()
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

    @GetMapping("create")
    public String getAddUserForm(Model model){
        model.addAttribute("user", new User());
        return "admin-create-account";
    }

    @PostMapping("create")
    public String addNewUser(@ModelAttribute User user, Model model){
        user.setEnabled(true);
        user.setIsActive(true);
        userService.createNewUser(user);
        model.addAttribute("message", "Add user success");
        return "redirect:/admin/users";
    }
}
