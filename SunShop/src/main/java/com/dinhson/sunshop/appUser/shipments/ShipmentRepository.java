package com.dinhson.sunshop.appUser.shipments;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShipmentRepository extends CrudRepository<Shipment, Integer> {

    @Query("SELECT s FROM Shipment s WHERE s.user.id = :userId")
    List<Shipment> getShipmentsByUserId(Integer userId);
}
