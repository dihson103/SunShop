package com.dinhson.sunshop.appOrders;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Integer> {

    @Query("SELECT o FROM Order o")
    List<Order> getAllOrders();

    @Query("SELECT o FROM Order o WHERE o.status = :status")
    List<Order> searchOrdersByStatus(Status status);

    @Query("SELECT o FROM Order o WHERE o.shipment.user.email LIKE %:searchName%")
    List<Order> searchOrdersBySearchName(String searchName);

    @Query("SELECT o FROM Order o WHERE o.shipment.user.email LIKE %:searchName% AND o.status = :status")
    List<Order> searchOrdersByAll(Status status, String searchName);
}
