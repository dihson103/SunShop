package com.dinhson.sunshop.appAdmin.ordersManagement;

import com.dinhson.sunshop.appOrders.Order;
import com.dinhson.sunshop.appOrders.orderdetails.OrderDetail;
import com.dinhson.sunshop.appOrders.orderdetails.OrderDetailService;
import com.dinhson.sunshop.appUser.User;
import com.dinhson.sunshop.appUser.shipments.Shipment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
@AllArgsConstructor
public class OrderResponseMapper implements Function<Order, OrderResponse> {

    private final OrderDetailResponseMapper orderDetailResponseMapper;
    private final OrderDetailService orderDetailService;

    @Override
    public OrderResponse apply(Order order) {
        List<OrderDetail> orderDetail = orderDetailService.getOrderDetailsByOrder(order);
        Shipment shipment = order.getShipment();
        User user = shipment.getUser();

        return OrderResponse
                .builder()
                .orderId(order.getId())
                .customerName(user.getName())
                .customerEmail(user.getEmail())
                .phone(shipment.getPhone())
                .address(shipment.getAddress())
                .orderDetails(orderDetail.stream().map(orderDetailResponseMapper).toList())
                .orderDate(order.getOrderDate())
                .status(order.getStatus())
                .total(orderDetail.stream()
                        .mapToDouble(value -> value.getQuantity() * value.getProductDetail().getProduct().getPrice())
                        .sum())
                .build();
    }
}
