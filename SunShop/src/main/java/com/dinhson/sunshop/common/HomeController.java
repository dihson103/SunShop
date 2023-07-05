package com.dinhson.sunshop.common;

import com.dinhson.sunshop.appProduct.categories.Category;
import com.dinhson.sunshop.appProduct.categories.CategoryService;
import com.dinhson.sunshop.securityConfig.MyUserDetail;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@AllArgsConstructor
@SessionAttributes("user")
public class HomeController {

    private final CategoryService categoryService;

    @GetMapping("home")
    public String home(Model model) {
        Iterable<Category> categories = categoryService.findAllCategory();

        model.addAttribute("categories", categories);
        model.addAttribute("isHome", true);
        return "home";
    }

    @GetMapping("login")
    public String getLogin(@AuthenticationPrincipal MyUserDetail user){
        if(user != null){
            switch (user.getRole()){
                case USER -> {
                    return "redirect:/home";
                }
                case ADMIN, MANAGER -> {
                    return "redirect:/admin/users";
                }
            }
        }
        return "login";
    }

    @ModelAttribute("user")
    public UserDetails name(@AuthenticationPrincipal UserDetails user) {
        return user;
    }
}
