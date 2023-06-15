package com.dinhson.sunshop.appCart;

import com.dinhson.sunshop.appProduct.Product;
import com.dinhson.sunshop.appProduct.ProductResponseDTO;
import com.dinhson.sunshop.appProduct.ProductResponseDTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class CartItemDTOMapper implements Function<CartItem, CartItemDTO> {

    private final ProductResponseDTOMapper productResponseDTOMapper;

    @Override
    public CartItemDTO apply(CartItem cartItem) {
        Product product = cartItem.getProductDetail().getProduct();
        ProductResponseDTO productResponseDTO = productResponseDTOMapper.apply(product);
        return new CartItemDTO(
                cartItem.getId(),
                product.getName(),
                product.getImg(),
                cartItem.getQuantity(),
                product.getPrice(),
                productResponseDTO.discount(),
                cartItem.getProductDetail().getSize().getId(),
                cartItem.getProductDetail().getColor().getId(),
                productResponseDTO.colors(),
                productResponseDTO.sizes()
        );
    }
}
