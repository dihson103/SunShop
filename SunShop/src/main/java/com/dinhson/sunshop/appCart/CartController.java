package com.dinhson.sunshop.appCart;

import com.dinhson.sunshop.securityConfig.MyUserDetail;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public String getCart(@AuthenticationPrincipal MyUserDetail user, Model model) {
        List<CartItemDTO> cart = cartService.findCartByUserId(user.getId());

        model.addAttribute("cart", cart);
        return "shopping-cart";
    }
}
