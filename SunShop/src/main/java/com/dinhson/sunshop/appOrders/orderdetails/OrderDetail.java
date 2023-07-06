package com.dinhson.sunshop.appOrders.orderdetails;

import com.dinhson.sunshop.appOrders.Order;
import com.dinhson.sunshop.appProduct.productDetails.ProductDetail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_details")
@Builder
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer quantity;

    private Integer discount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_detail_id", nullable = false)
    private ProductDetail productDetail;

    @ManyToOne()
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderDetail (Integer quantity, Integer discount, ProductDetail productDetail, Order order){
        this.quantity = quantity;
        this.discount = discount;
        this.productDetail = productDetail;
        this.order = order;
    }
}
