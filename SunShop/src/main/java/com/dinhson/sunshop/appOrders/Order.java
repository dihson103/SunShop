package com.dinhson.sunshop.appOrders;

import com.dinhson.sunshop.appOrders.orderdetails.OrderDetail;
import com.dinhson.sunshop.appUser.shipments.Shipment;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
@Builder
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order")
    private Set<OrderDetail> orderDetails;

}