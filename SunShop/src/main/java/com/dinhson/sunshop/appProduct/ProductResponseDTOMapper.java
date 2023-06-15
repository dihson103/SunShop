package com.dinhson.sunshop.appProduct;

import com.dinhson.sunshop.appProduct.productDetails.ProductDetailResponseDTO;
import com.dinhson.sunshop.appProduct.productDetails.ProductDetailResponseDTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ProductResponseDTOMapper implements Function<Product, ProductResponseDTO> {

    private final ProductDTOMapper productDTOMapper;
    private final ProductDetailResponseDTOMapper productDetailResponseDTOMapper;

    @Override
    public ProductResponseDTO apply(Product product) {
        return new ProductResponseDTO(
                productDTOMapper.apply(product),

                product.getProductDetails().stream()
                        .flatMap(p -> p.getImages().stream())
                        .collect(Collectors.toSet()),

                product.getProductDetails().stream()
                        .map(p -> p.getColor())
                        .collect(Collectors.toSet()),

                product.getProductDetails().stream()
                        .map(p -> p.getSize())
                        .collect(Collectors.toSet()),

                //TODO Viet ham tinh toan discount
                0,

                product.getProductDetails().stream()
                        .map(productDetailResponseDTOMapper)
                        .collect(Collectors.toList())
        );
    }
}
