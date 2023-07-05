package com.dinhson.sunshop.appCart;

import com.dinhson.sunshop.common.ApiResponse;
import com.dinhson.sunshop.securityConfig.MyUserDetail;
import jakarta.annotation.security.PermitAll;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/cart")
public class CartApiController {

    private final CartService cartService;

    @PostMapping
    public ApiResponse addItemToCart(@RequestParam(name = "productDetailId") Integer productDetailId,
                                     @RequestParam Integer numberProduct,
                                     @AuthenticationPrincipal MyUserDetail user,
                                     Model model) {

        cartService.addItemsToCart(productDetailId, user.getId(), numberProduct);

        return new ApiResponse("Add success!!", HttpStatus.OK);
    }

    @PutMapping
    public String changeQuantityItem(@RequestBody CartItemRequestDTO cartItemRequestDTO,
                                     @AuthenticationPrincipal MyUserDetail user) {

        cartService.changeQuantityItem(cartItemRequestDTO.productDetailId(), user.getId(), cartItemRequestDTO.number());

        return "Change success!!!";
    }

    @DeleteMapping
    public String deleteItem(@RequestBody CartItemRequestDTO cartItemRequestDTO,
                             @AuthenticationPrincipal MyUserDetail user){

        cartService.deleteItem(cartItemRequestDTO.productDetailId(), user.getId());
        return "Success";
    }

    @PutMapping("change")
    public String changeItemInCart(@RequestBody CartItemRequestDTO cartItemRequestDTO){

        return "Success";
    }

}
