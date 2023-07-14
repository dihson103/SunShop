package com.dinhson.sunshop.appProduct;

import com.dinhson.sunshop.appProduct.colors.Color;
import com.dinhson.sunshop.appProduct.images.Image;
import com.dinhson.sunshop.appProduct.productDetails.ProductDetail;
import com.dinhson.sunshop.appProduct.productDetails.ProductDetailResponseDTO;
import com.dinhson.sunshop.appProduct.productDetails.ProductDetailResponseDTOMapper;
import com.dinhson.sunshop.appProduct.sizes.Size;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ProductResponseDTOMapper implements Function<Product, ProductResponseDTO> {

    private final ProductDTOMapper productDTOMapper;
    private final ProductDetailResponseDTOMapper productDetailResponseDTOMapper;

    @Override
    public ProductResponseDTO apply(Product product) {
        return ProductResponseDTO
                .builder()
                .productDTO(productDTOMapper.apply(product))
                .images(getImageSet(product))
                .colors(getColorSet(product))
                .sizes(getSizeSet(product))
                .discount(0) //TODO Viet ham tinh toan discount
                .productDetailResponseDTO(getProductDetailResponseDTOList(product))
                .build();
    }

    private List<ProductDetailResponseDTO> getProductDetailResponseDTOList(Product product) {
        return product.getProductDetails().stream()
                .map(productDetailResponseDTOMapper)
                .collect(Collectors.toList());
    }

    private Set<Size> getSizeSet(Product product) {
        return product.getProductDetails().stream()
                .map(ProductDetail::getSize)
                .collect(Collectors.toSet());
    }

    private Set<Color> getColorSet(Product product) {
        return product.getProductDetails().stream()
                .map(ProductDetail::getColor)
                .collect(Collectors.toSet());
    }

    private Set<Image> getImageSet(Product product) {
        return product.getProductDetails().stream()
                .flatMap(p -> p.getImages().stream())
                .collect(Collectors.toSet());
    }
}
