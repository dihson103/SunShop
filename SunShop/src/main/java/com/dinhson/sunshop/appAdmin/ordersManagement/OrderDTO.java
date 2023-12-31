package com.dinhson.sunshop.appAdmin.ordersManagement;

import com.dinhson.sunshop.appOrders.Status;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record OrderDTO(
        Integer orderId,
        String userName,
        String email,
        LocalDate orderDate,
        Status status,
        Double totalMoney
) {
}
