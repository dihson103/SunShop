package com.dinhson.sunshop.appUser.shipments;

import com.dinhson.sunshop.appUser.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "shipments")
@AllArgsConstructor
@NoArgsConstructor
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String phone;

    private String address;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Shipment (String phone, String address, User user){
        this.phone = phone;
        this.address = address;
        this.user = user;
    }
}