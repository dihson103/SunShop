package com.dinhson.sunshop.appOrders;

import com.dinhson.sunshop.appAdmin.ordersManagement.OrderDetailResponse;
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
        int statusInNumber,
        Status status,
        Double total

) {
}
