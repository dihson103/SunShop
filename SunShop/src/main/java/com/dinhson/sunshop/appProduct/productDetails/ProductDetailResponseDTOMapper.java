package com.dinhson.sunshop.appProduct.productDetails;

import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProductDetailResponseDTOMapper implements Function<ProductDetail, ProductDetailResponseDTO> {
    @Override
    public ProductDetailResponseDTO apply(ProductDetail productDetail) {
        return ProductDetailResponseDTO
                .builder()
                .productDetailId(productDetail.getId())
                .colorId(productDetail.getColor().getId())
                .sizeId(productDetail.getSize().getId())
                .number(productDetail.getNumber())
                .build();
    }
}
