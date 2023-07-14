package com.dinhson.sunshop.appProduct.sizes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sizes")
@Builder
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String size;

    private String height;

    private String weight;

//    public Size(String size, String height, String weight) {
//        this.size = size;
//        this.height = height;
//        this.weight = weight;
//    }
}
