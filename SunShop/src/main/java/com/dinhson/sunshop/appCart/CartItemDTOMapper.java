package com.dinhson.sunshop.appCart;

import com.dinhson.sunshop.appProduct.Product;
import com.dinhson.sunshop.appProduct.ProductDTO;
import com.dinhson.sunshop.appProduct.productDetails.ProductDetailDTO;
import com.dinhson.sunshop.appProduct.productDetails.ProductDetailDTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class CartItemDTOMapper implements Function<CartItem, CartItemDTO> {

    private final ProductDetailDTOMapper productDetailDTOMapper;

    @Override
    public CartItemDTO apply(CartItem cartItem) {
        Product product = cartItem.getProductDetail().getProduct();
        ProductDetailDTO productDetailDTO = productDetailDTOMapper.apply(product);
        return new CartItemDTO(
                cartItem.getId(),
                product.getName(),
                product.getImg(),
                cartItem.getQuantity(),
                product.getPrice(),
                productDetailDTO.discount(),
                cartItem.getProductDetail().getSize().getId(),
                cartItem.getProductDetail().getColor().getId(),
                productDetailDTO.colors(),
                productDetailDTO.sizes()
        );
    }
}
