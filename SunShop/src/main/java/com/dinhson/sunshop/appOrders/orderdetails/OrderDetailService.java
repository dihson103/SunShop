package com.dinhson.sunshop.appOrders.orderdetails;

import com.dinhson.sunshop.appCart.CartItem;
import com.dinhson.sunshop.appOrders.Order;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    private void addNewOrders(CartItem cartItem, Order order){
        //TODO van dang de discount la 0
        OrderDetail orderDetail = OrderDetail.builder()
                .quantity(cartItem.getQuantity())
                .discount(0)
                .productDetail(cartItem.getProductDetail())
                .order(order)
                .build();
                //new OrderDetail(cartItem.getQuantity(), 0, cartItem.getProductDetail(), order);
        orderDetailRepository.save(orderDetail);
    }

    public void creatOrderDetails(List<CartItem> cartItems, Order order){
        cartItems.forEach(cartItem -> {
            addNewOrders(cartItem, order);
        });
    }

    public List<OrderDetail> getOrderDetailsByOrder(Order order){
        return orderDetailRepository.findOrderDetailsByOrder(order);
    }

}
