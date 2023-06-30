package com.dinhson.sunshop.appOrders;

import com.dinhson.sunshop.appOrders.orderdetails.OrderDetail;
import com.dinhson.sunshop.appUser.shipments.Shipment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate orderDate;

    private Status status;

    private String note;

    @ManyToOne
    @JoinColumn(name = "shipment_id")
    private Shipment shipment;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Set<OrderDetail> orderDetails;

    public Order (LocalDate orderDate, Status status, String note, Shipment shipment){
        this.orderDate = orderDate;
        this.status = status;
        this.note = note;
        this.shipment = shipment;
    }
}