package com.dinhson.sunshop.appOrders.orderdetails;

import com.dinhson.sunshop.appOrders.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderDetailRepository extends CrudRepository<OrderDetail, Integer> {

    List<OrderDetail> findOrderDetailsByOrder(Order order);
}
