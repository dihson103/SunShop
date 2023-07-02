package com.dinhson.sunshop.appAdmin.ordersManagement;

import com.dinhson.sunshop.appOrders.Order;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class OrderDTOMapper implements Function<Order, OrderDTO> {
    @Override
    public OrderDTO apply(Order order) {

        System.out.println("order number: " + order.getId() + " has " + order.getOrderDetails().size());

        return new OrderDTO(
                order.getId(),
                order.getShipment().getUser().getName(),
                order.getShipment().getUser().getEmail(),
                order.getOrderDate(),
                order.getStatus(),
                //TODO chua tinh discount
                order.getOrderDetails().stream()
                        .mapToDouble(value -> {
                            return value.getQuantity() * value.getProductDetail().getProduct().getPrice();
                        })
                        .sum()
                );
    }
}
