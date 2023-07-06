package com.dinhson.sunshop.appOrders;

import lombok.Builder;

@Builder
public record OrderItemDTO(
        String information,
        String color,
        String size,
        Double totalPrice
) {
}
