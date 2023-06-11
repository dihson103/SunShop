package com.dinhson.sunshop.appProduct;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("shop")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String listProduct(Model model){
        return "shop-grid";
    }
}
