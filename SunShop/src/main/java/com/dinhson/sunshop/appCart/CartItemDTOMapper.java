package com.dinhson.sunshop.appCart;

import com.dinhson.sunshop.appProduct.Product;
import com.dinhson.sunshop.appProduct.ProductResponseDTO;
import com.dinhson.sunshop.appProduct.ProductResponseDTOMapper;
import com.dinhson.sunshop.appProduct.productDetails.ProductDetail;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class CartItemDTOMapper implements Function<CartItem, CartItemDTO> {

    private final ProductResponseDTOMapper productResponseDTOMapper;

    @Override
    public CartItemDTO apply(CartItem cartItem) {
        ProductDetail productDetail = cartItem.getProductDetail();
        Product product = productDetail.getProduct();
        ProductResponseDTO productResponseDTO = productResponseDTOMapper.apply(product);

        return CartItemDTO
                .builder()
                .id(cartItem.getId())
                .productId(product.getId())
                .productDetailId(productDetail.getId())
                .name(product.getName())
                .img(product.getImg())
                .quantity(cartItem.getQuantity())
                .price(product.getPrice())
                .discount(productResponseDTO.discount())
                .sizeIdChoose(productDetail.getSize().getId())
                .colorIdChoose(productDetail.getColor().getId())
                .colors(productResponseDTO.colors())
                .sizes(productResponseDTO.sizes())
                .build();
    }
}
