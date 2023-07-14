package com.dinhson.sunshop.appOrders;

import com.dinhson.sunshop.appAdmin.ordersManagement.OrderDetailResponse;
import com.dinhson.sunshop.appAdmin.ordersManagement.OrderDetailResponseMapper;
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
        List<OrderDetail> orderDetails = orderDetailService.getOrderDetailsByOrder(order);
        Shipment shipment = order.getShipment();
        User user = shipment.getUser();

        return OrderResponse
                .builder()
                .orderId(order.getId())
                .customerName(user.getName())
                .customerEmail(user.getEmail())
                .phone(shipment.getPhone())
                .address(shipment.getAddress())
                .orderDetails(getOrderDetailResponses(orderDetails))
                .orderDate(order.getOrderDate())
                .statusInNumber(getStatusInNumber(order.getStatus()))
                .status(order.getStatus())
                .total(calculateTotalPrice(orderDetails))
                .build();
    }

    private int getStatusInNumber(Status status){
        switch (status){
            case Pending -> {
                return 1;
            }
            case Shipped -> {
                return 2;
            }
            case Delivered -> {
                return 3;
            }
            case Returns -> {
                return 4;
            }
        }
        return 0;
    }

    private List<OrderDetailResponse> getOrderDetailResponses (List<OrderDetail> orderDetails){
        return orderDetails.stream()
                .map(orderDetailResponseMapper)
                .toList();
    }

    private Double calculateTotalPrice (List<OrderDetail> orderDetails) {
        return orderDetails.stream()
                .mapToDouble(value -> value.getQuantity() * value.getProductDetail().getProduct().getPrice())
                .sum();
    }
}
