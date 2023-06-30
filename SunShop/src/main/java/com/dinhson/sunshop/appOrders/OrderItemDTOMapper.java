package com.dinhson.sunshop.appOrders;

import com.dinhson.sunshop.appCart.CartItem;
import com.dinhson.sunshop.appProduct.Product;
import com.dinhson.sunshop.appProduct.productDetails.ProductDetail;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class OrderItemDTOMapper implements Function<CartItem, OrderItemDTO> {
    @Override
    public OrderItemDTO apply(CartItem cartItem) {
        ProductDetail productDetail = cartItem.getProductDetail();
        Product product = productDetail.getProduct();
        return new OrderItemDTO(
                product.getName() + " (x" + cartItem.getQuantity() + ")",
                productDetail.getColor().getName(),
                productDetail.getSize().getSize(),
                //TODO chua tinh discount
                cartItem.getQuantity() * product.getPrice()
        );
    }
}
