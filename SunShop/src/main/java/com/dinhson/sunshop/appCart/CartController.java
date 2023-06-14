package com.dinhson.sunshop.appCart;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
@EnableCaching
public class CartController {

    private final CartService cartService;

    @GetMapping("cart")
    public String getCart(){
        return "shopping-cart";
    }
}
