package com.dinhson.sunshop.appOrders;

import com.dinhson.sunshop.appOrders.orderdetails.OrderDetail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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

    private LocalDateTime orderDate;

    private Status status;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Set<OrderDetail> orderDetails;
}