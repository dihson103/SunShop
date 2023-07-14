package com.dinhson.sunshop.appProduct;

import com.dinhson.sunshop.appProduct.colors.Color;
import com.dinhson.sunshop.appProduct.productDetails.ProductDetail;
import com.dinhson.sunshop.appProduct.sizes.Size;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ProductDetailMapProductResponseDTOMapper implements Function<ProductDetail, ProductResponseDTO> {

    private final ProductDTOMapper productDTOMapper;

    @Override
    public ProductResponseDTO apply(ProductDetail productDetail) {
        return ProductResponseDTO
                .builder()
                .productDTO(productDTOMapper.apply(productDetail.getProduct()))
                .colors(getColorSet(productDetail))
                .sizes(getSizeSet(productDetail))
                .discount(0)
                .build();
    }

    private Set<Size> getSizeSet(ProductDetail productDetail) {
        return productDetail.getProduct().getProductDetails().stream()
                .map(ProductDetail::getSize)
                .collect(Collectors.toSet());
    }

    private Set<Color> getColorSet(ProductDetail productDetail) {
        return productDetail.getProduct().getProductDetails().stream()
                .map(ProductDetail::getColor)
                .collect(Collectors.toSet());
    }
}
