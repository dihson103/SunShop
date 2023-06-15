package com.dinhson.sunshop.common;

import com.dinhson.sunshop.appProduct.categories.Category;
import com.dinhson.sunshop.appProduct.categories.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class HomeController {

    private final CategoryService categoryService;

    @GetMapping("home")
    public String home(Model model) {
        Iterable<Category> categories = categoryService.findAllCategory();

        model.addAttribute("categories", categories);
        model.addAttribute("isHome", true);
        return "home";
    }
}
