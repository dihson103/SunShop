package com.dinhson.sunshop.appCart;

import com.dinhson.sunshop.appProduct.colors.Color;
import com.dinhson.sunshop.appProduct.sizes.Size;

import java.util.Set;

public record CartItemDTO(
        int id,
        int productId,
        int productDetailId,
        String name,
        String img,
        int quantity,
        double price,
        int discount,
        int sizeIdChoose,
        int colorIdChoose,
        Set<Color> colors,
        Set<Size> sizes
) {
}
