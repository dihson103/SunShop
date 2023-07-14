package com.dinhson.sunshop.appOrders;

import com.dinhson.sunshop.appUser.shipments.Shipment;
import com.dinhson.sunshop.appUser.shipments.ShipmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository underTest;

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Test
    void testAddNewOrderReturnIdValueOrNot(){
        //before
        int userId = 4;
        LocalDate orderDate = LocalDate.now();
        Status status = Status.Pending;
        Shipment shipment = shipmentRepository.findById(1).get();
        String note = "giao hang som nha";
        Order order = null; //new Order(orderDate, status, note, shipment);

        //when
        Order orderAdd = underTest.save(order);

        //then
        System.out.println("order: " + orderAdd.getId());
        assertNotEquals(null, orderAdd);
    }


}