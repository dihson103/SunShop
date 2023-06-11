package com.dinhson.sunshop.appOrders.votes;

import com.dinhson.sunshop.appOrders.Order;
import com.dinhson.sunshop.appUser.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "votes")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int star;

    private String comment;

    private LocalDateTime dateCreat;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Order order;
}
