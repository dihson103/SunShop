package com.dinhson.sunshop.appProduct.images;

import com.dinhson.sunshop.appProduct.productDetails.ProductDetail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String img;

    @ManyToOne
    @JoinColumn(name = "product_detail_id")
    private ProductDetail productDetail;

    public Image(String img, ProductDetail productDetail) {
        this.img = img;
        this.productDetail = productDetail;
    }
}