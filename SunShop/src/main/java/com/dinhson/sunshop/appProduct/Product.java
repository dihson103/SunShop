package com.dinhson.sunshop.appProduct;

import com.dinhson.sunshop.appAdmin.productManagement.ProductRequestCreate;
import com.dinhson.sunshop.appProduct.categories.Category;
import com.dinhson.sunshop.appProduct.productDetails.ProductDetail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "Text")
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private LocalDateTime createDate;

    @Column(nullable = false)
    private boolean isDelete;

    @Column(nullable = true)
    private String img;

    @ManyToOne()
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Set<ProductDetail> productDetails;

    public Product(ProductRequestCreate productRequestCreate, Category category, String img){
        name = productRequestCreate.getName();
        price = productRequestCreate.getPrice();
        description = productRequestCreate.getDescription();
        isDelete = true;
        this.img = img;
        this.category = category;
        createDate = LocalDateTime.now();
    }
}