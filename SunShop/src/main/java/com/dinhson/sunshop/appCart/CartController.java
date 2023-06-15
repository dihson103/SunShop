package com.dinhson.sunshop.appCart;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@EnableCaching
public class CartController {

    private final CartService cartService;

    @GetMapping("cart")
    public String getCart(Model model) {
        int userId = 1;

        List<CartItemDTO> cart = cartService.findCartByUserId(userId);

        model.addAttribute("cart", cart);
        return "shopping-cart";
    }
}
