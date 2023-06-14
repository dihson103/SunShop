package com.dinhson.sunshop.appProduct.productDetails;

import com.dinhson.sunshop.appProduct.Product;
import com.dinhson.sunshop.appProduct.ProductDTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ProductDetailDTOMapper implements Function<Product, ProductDetailDTO> {

    private final ProductDTOMapper productDTOMapper;

    @Override
    public ProductDetailDTO apply(Product product) {
        return new ProductDetailDTO(
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
                0
        );
    }
}
