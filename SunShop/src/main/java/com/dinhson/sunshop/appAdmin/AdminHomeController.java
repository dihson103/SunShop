package com.dinhson.sunshop.appAdmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/home")
public class AdminHomeController {

    @GetMapping
    public String adminHome(){
        return "admin-home";
    }

}
