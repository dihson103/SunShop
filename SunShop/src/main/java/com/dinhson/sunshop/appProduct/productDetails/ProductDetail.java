package com.dinhson.sunshop.appProduct.productDetails;

import com.dinhson.sunshop.appProduct.Product;
import com.dinhson.sunshop.appProduct.colors.Color;
import com.dinhson.sunshop.appProduct.discounts.Discount;
import com.dinhson.sunshop.appProduct.images.Image;
import com.dinhson.sunshop.appProduct.sizes.Size;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_details")
public class ProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    @ManyToOne
    @JoinColumn(name = "size_id")
    private Size size;

    private int number;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_detail_id")
    private Set<Image> images;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_detail_id")
    private Set<Discount> discounts;

    public ProductDetail (Product product, Color color, Size size, Integer number){
        this.product = product;
        this.color = color;
        this.size = size;
        this.number = number;
    }
}
