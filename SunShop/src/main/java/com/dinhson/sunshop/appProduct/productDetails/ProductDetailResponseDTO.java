package com.dinhson.sunshop.appProduct.productDetails;

import lombok.Builder;

@Builder
public record ProductDetailResponseDTO(
        int productDetailId,
        int colorId,
        int sizeId,
        int number
) {
}
