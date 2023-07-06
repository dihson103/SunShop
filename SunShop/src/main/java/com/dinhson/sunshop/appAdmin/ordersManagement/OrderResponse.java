package com.dinhson.sunshop.appAdmin.ordersManagement;

import com.dinhson.sunshop.appOrders.Status;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record OrderResponse(
        Integer orderId,
        String customerName,
        String customerEmail,
        String phone,
        String address,
        List<OrderDetailResponse> orderDetails,
        LocalDate orderDate,
        Status status,
        Double total

) {
}
