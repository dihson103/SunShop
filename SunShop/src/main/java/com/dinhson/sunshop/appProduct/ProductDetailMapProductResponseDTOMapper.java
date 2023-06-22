package com.dinhson.sunshop.appProduct;

import com.dinhson.sunshop.appProduct.productDetails.ProductDetail;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ProductDetailMapProductResponseDTOMapper implements Function<ProductDetail, ProductResponseDTO> {

    private final ProductDTOMapper productDTOMapper;

    @Override
    public ProductResponseDTO apply(ProductDetail productDetail) {
        return new ProductResponseDTO(
                productDTOMapper.apply(productDetail.getProduct()),
                null,
                productDetail.getProduct().getProductDetails().stream()
                        .map(p ->p.getColor())
                        .collect(Collectors.toSet()),
                productDetail.getProduct().getProductDetails().stream()
                        .map(p ->p.getSize())
                        .collect(Collectors.toSet()),
                0,
                null
                );
    }
}
