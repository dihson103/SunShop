package com.dinhson.sunshop.appCart;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/cart")
@EnableCaching
public class CartApiController {

    private final CartService cartService;

    @PostMapping
    public String addItemToCart(@RequestParam(name = "productDetailId") Integer productDetailId,
                                Model model) {

        //TODO lay userId

        cartService.addItemsToCart(productDetailId, 4);
        int count = cartService.countNumberItemInCart(4);

        return String.valueOf(count);
    }

    @PutMapping
    public String changeQuantityItem(@RequestBody CartItemRequestDTO cartItemRequestDTO) {

        cartService.changeQuantityItem(cartItemRequestDTO.productDetailId(), 4, cartItemRequestDTO.number());

        return "Change success!!!";
    }

    @DeleteMapping
    public String deleteItem(@RequestBody CartItemRequestDTO cartItemRequestDTO){

        int userId = 4;
        cartService.deleteItem(cartItemRequestDTO.productDetailId(), userId);
        return "Success";
    }

    @PutMapping("change")
    public String changeItemInCart(@RequestBody CartItemRequestDTO cartItemRequestDTO){

        return "Success";
    }

}
