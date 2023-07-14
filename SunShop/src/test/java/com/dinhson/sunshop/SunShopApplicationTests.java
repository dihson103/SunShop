package com.dinhson.sunshop;

import com.dinhson.sunshop.appOrders.Order;
import com.dinhson.sunshop.appOrders.OrderRepository;
import com.dinhson.sunshop.appOrders.orderdetails.OrderDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

@SpringBootTest
class SunShopApplicationTests {

	@Autowired
	private OrderRepository orderRepository;

	@Test
	void contextLoads() throws ParseException {
		Order order = orderRepository.findById(1).orElse(null);
		Set<OrderDetail> orderDetails = order.getOrderDetails();

		System.out.println(orderDetails.size());
	}

}
