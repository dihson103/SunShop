package com.dinhson.sunshop.appProduct.productDetails;

import com.dinhson.sunshop.appProduct.ProductDTO;
import com.dinhson.sunshop.appProduct.colors.Color;
import com.dinhson.sunshop.appProduct.images.Image;
import com.dinhson.sunshop.appProduct.sizes.Size;

import java.util.Set;

public record ProductDetailDTO(
        ProductDTO productDTO,
        Set<Image> images,
        Set<Color> colors,
        Set<Size> sizes,
        int discount
) {
}
