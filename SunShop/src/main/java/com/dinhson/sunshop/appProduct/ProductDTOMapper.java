package com.dinhson.sunshop.appProduct;

import com.dinhson.sunshop.appProduct.images.Image;
import org.springframework.stereotype.Component;

import java.util.Set;
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
                .images(getSetProductImage(product))
                .isDelete(product.isDelete())
                .category(product.getCategory())
                .build();
    }

    private Set<Image> getSetProductImage(Product product) {
        return product.getProductDetails().stream()
                .flatMap(p -> p.getImages().stream())
                .collect(Collectors.toSet());
    }
}
