package com.dinhson.sunshop.appCart;

import com.dinhson.sunshop.appProduct.productDetails.ProductDetail;
import com.dinhson.sunshop.appUser.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_detail_id")
    private ProductDetail productDetail;

//    public CartItem(int quantity, User user, ProductDetail productDetail) {
//        this.quantity = quantity;
//        this.user = user;
//        this.productDetail = productDetail;
//    }
}
