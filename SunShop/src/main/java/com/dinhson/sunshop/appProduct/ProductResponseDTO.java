package com.dinhson.sunshop.appProduct;

import com.dinhson.sunshop.appProduct.colors.Color;
import com.dinhson.sunshop.appProduct.images.Image;
import com.dinhson.sunshop.appProduct.productDetails.ProductDetailResponseDTO;
import com.dinhson.sunshop.appProduct.sizes.Size;
import lombok.Builder;

import java.util.List;
import java.util.Set;

@Builder
public record ProductResponseDTO(
        ProductDTO productDTO,
        Set<Image> images,
        Set<Color> colors,
        Set<Size> sizes,
        int discount,
        List<ProductDetailResponseDTO> productDetailResponseDTO

) {
}
