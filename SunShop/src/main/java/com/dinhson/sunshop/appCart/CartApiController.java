package com.dinhson.sunshop.appCart;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/cart")
@EnableCaching
public class CartApiController {

    private final CartService cartService;

    @PostMapping
    public String addItemToCart(@RequestParam(name = "product-detail-id") Integer productDetailId) {
        cartService.addItemsToCart(productDetailId, 1);
        int count = cartService.countNumberItemInCart(1);
        return String.valueOf(count);
    }

    @PutMapping
    public String changeQuantityItem(@RequestParam(name = "product-detail-id") Integer productDetailId,
                                     @RequestParam(name = "number") Integer number) {

        cartService.changeQuantityItem(productDetailId, 1, number);

        return "Change success!!!";
    }
}
