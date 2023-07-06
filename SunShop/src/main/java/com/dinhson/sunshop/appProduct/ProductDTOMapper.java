package com.dinhson.sunshop.appProduct;

import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ProductDTOMapper implements Function<Product, ProductDTO> {

    @Override
    public ProductDTO apply(Product product) {
        return ProductDTO
                .builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .img(product.getImg())
                .images(product.getProductDetails().stream()
                        .flatMap(p -> p.getImages().stream())
                        .collect(Collectors.toSet()))
                .isDelete(product.isDelete())
                .category(product.getCategory())
                .build();
    }
}
