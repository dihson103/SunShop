package com.dinhson.sunshop.appAdmin.ordersManagement;

import com.dinhson.sunshop.appOrders.Status;

import java.time.LocalDate;

public record OrderDTO(
        Integer orderId,
        String userName,
        String email,
        LocalDate orderDate,
        Status status,
        Double totalMoney
) {
}
