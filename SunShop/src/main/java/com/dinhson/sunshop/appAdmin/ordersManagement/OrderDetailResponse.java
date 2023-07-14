package com.dinhson.sunshop.appAdmin.ordersManagement;

import lombok.Builder;

@Builder
public record OrderDetailResponse(
        String productName,
        String productImage,
        String productColor,
        String productSize,
        Double price,
        Integer quantity
) {
}
