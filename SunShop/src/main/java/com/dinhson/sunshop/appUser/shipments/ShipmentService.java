package com.dinhson.sunshop.appUser.shipments;

import com.dinhson.sunshop.appUser.User;
import com.dinhson.sunshop.appUser.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final UserService userService;

    public List<Shipment> getShipmentsByUserId(Integer userId){
        return shipmentRepository.getShipmentsByUserId(userId);
    }

    public Shipment createNewShipment(String address, String phone, Integer userId){
        User user = userService.findUserById(userId);
        Shipment shipment = Shipment.builder()
                .phone(phone)
                .address(address)
                .user(user)
                .build();
                //new Shipment(phone, address, user);
        return shipmentRepository.save(shipment);
    }

    public Shipment findShipmentById(Integer shipmentId){
        return shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new IllegalArgumentException("Can not find shipment!!!"));
    }

}
