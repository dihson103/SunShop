package com.dinhson.sunshop.appAdmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderManagementController {

    @GetMapping("admin/home")
    public String adminHome(){
        return "admin-orders-management";
    }
}
