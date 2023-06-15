package com.dinhson.sunshop.appProduct.productDetails;

import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProductDetailResponseDTOMapper implements Function<ProductDetail, ProductDetailResponseDTO> {
    @Override
    public ProductDetailResponseDTO apply(ProductDetail productDetail) {
        return new ProductDetailResponseDTO(
                productDetail.getId(),
                productDetail.getColor().getId(),
                productDetail.getSize().getId(),
                productDetail.getNumber()
        );
    }
}
