package com.dinhson.sunshop.appProduct.discounts;

import com.dinhson.sunshop.appProduct.productDetails.ProductDetail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "discounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Integer discount;

    @Column(nullable = false)
    private LocalDateTime fromDate;

    @Column(nullable = false)
    private LocalDateTime toDate;

    @Column(nullable = false)
    private int number;

    @ManyToOne
    @JoinColumn(name = "product_detail_id")
    private ProductDetail productDetail;
}