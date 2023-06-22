package com.dinhson.sunshop.appAdmin.accountManagement;

import com.dinhson.sunshop.appUser.Role;
import com.dinhson.sunshop.appUser.UserDTO;
import com.dinhson.sunshop.appUser.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("admin/api/users")
public class AccountManagementApi {

    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> searchUsers(@RequestParam String name,
                              @RequestParam String role,
                              @RequestParam String isActive){
        return userService.searchUsers(isActive, role, name);
    }
}
