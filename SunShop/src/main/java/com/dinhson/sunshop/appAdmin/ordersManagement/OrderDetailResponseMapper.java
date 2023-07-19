package com.dinhson.sunshop.appAdmin.ordersManagement;

import com.dinhson.sunshop.appOrders.orderdetails.OrderDetail;
import com.dinhson.sunshop.appProduct.Product;
import com.dinhson.sunshop.appProduct.productDetails.ProductDetail;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class OrderDetailResponseMapper implements Function<OrderDetail, OrderDetailResponse> {

    @Override
    public OrderDetailResponse apply(OrderDetail orderDetail) {
        ProductDetail productDetail = orderDetail.getProductDetail();
        Product product = productDetail.getProduct();

        return OrderDetailResponse
                .builder()
                .productId(product.getId())
                .productDetailId(productDetail.getId())
                .productName(product.getName())
                .productImage(product.getImg())
                .productColor(productDetail.getColor().getName())
                .productSize(productDetail.getSize().getSize())
                .price(product.getPrice())
                .quantity(orderDetail.getQuantity())
                .build();
    }
}
