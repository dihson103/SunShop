package com.dinhson.sunshop.appAdmin.ordersManagement;

import com.dinhson.sunshop.appOrders.Order;
import com.dinhson.sunshop.appOrders.orderdetails.OrderDetail;
import com.dinhson.sunshop.appOrders.orderdetails.OrderDetailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
@AllArgsConstructor
public class OrderDTOMapper implements Function<Order, OrderDTO> {

    private final OrderDetailService orderDetailService;

    @Override
    public OrderDTO apply(Order order) {
        return OrderDTO
                .builder()
                .orderId(order.getId())
                .userName(order.getShipment().getUser().getName())
                .email(order.getShipment().getUser().getEmail())
                .orderDate(order.getOrderDate())
                .status(order.getStatus())
                //TODO chua tinh discount
                .totalMoney(calculateTotalPrice(order))
                .build();
    }

    private Double calculateTotalPrice (Order order){
        List<OrderDetail> orderDetails = orderDetailService.getOrderDetailsByOrder(order);

        return order.getOrderDetails().stream()
                .mapToDouble(value -> value.getQuantity() * value.getProductDetail().getProduct().getPrice())
                .sum();
    }
}
