package com.dinhson.sunshop.appOrders.orderdetails;

import com.dinhson.sunshop.appProduct.productDetails.ProductDetail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_details")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer quantity;

    private Integer discount;

    @ManyToOne
    @JoinColumn(name = "product_detail_id", nullable = false)
    private ProductDetail productDetail;

    public OrderDetail (Integer quantity, Integer discount, ProductDetail productDetail){
        this.quantity = quantity;
        this.discount = discount;
        this.productDetail = productDetail;
    }
}
