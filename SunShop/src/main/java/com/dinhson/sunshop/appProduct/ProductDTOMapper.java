package com.dinhson.sunshop.appProduct;

import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ProductDTOMapper implements Function<Product, ProductDTO> {

    @Override
    public ProductDTO apply(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getImg(),
                product.getProductDetails().stream()
                        .flatMap(p -> p.getImages().stream())
                        .collect(Collectors.toSet())
        );
    }
}
