package com.dinhson.sunshop.appOrders;

public record OrderItemDTO(
        String information,
        String color,
        String size,
        Double totalPrice
) {
}
