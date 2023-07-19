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
                           @RequestParam(required = false, defaultValue = "") String message,
                           @RequestParam(required = false, defaultValue = "0") Integer pageIndex,
                           @RequestParam(required = false, defaultValue = "2") Integer pageSize,
                           Model model){
        List<UserDTO> userDTOS = userService.searchUsers(isActive, role, name, pageIndex, pageSize);
        Integer totalPages = userService.getTotalPages();
        model.addAttribute("users", userDTOS);
        model.addAttribute("role", role);
        model.addAttribute("isActive", isActive);
        model.addAttribute("search", name);
        model.addAttribute("message", message);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("pageIndex", pageIndex);
        return "admin-accounts-management";
    }

    @GetMapping("create")
    public String getAddUserForm(Model model){
        model.addAttribute("user", new User());
        return "admin-create-account";
    }

    @PostMapping("create")
    public String addNewUser(@ModelAttribute User user){
        user.setEnabled(true);
        user.setIsActive(true);
        userService.createNewUser(user);
        return "redirect:/admin/users?message=Add+user+success%21%21%21";
    }

    @GetMapping("{userId}")
    public String getUserDetails(Model model, @PathVariable Integer userId){
        UserDTO userDTO = userService.getUserDTO(userId);
        model.addAttribute("user", userDTO);
        return "admin-view-account-details";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("userId") Integer userId) {
        userService.changeAccountStatus(userId);
        return "redirect:/admin/users?message=Change+user's+status+success%21%21%21";
    }

}
