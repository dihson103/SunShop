package com.dinhson.sunshop.appCart;

public record CartItemRequestDTO(
        Integer productDetailId,
        Integer number,
        Integer colorId,
        Integer sizeId,
        Integer productId
) {
}
