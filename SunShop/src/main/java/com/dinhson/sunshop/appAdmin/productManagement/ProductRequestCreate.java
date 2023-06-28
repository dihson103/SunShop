package com.dinhson.sunshop.appAdmin.productManagement;

import com.dinhson.sunshop.appProduct.Product;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestCreate {

    @NotNull(message = "Product name must not be empty!!")
    private String name;

    @NotNull(message = "Category must not be empty")
    private Integer categoryId;

    @NotNull(message = "Price must not be empty")
    private Double price;

    @NotNull(message = "Description must not be empty")
    private String description;

    @NotNull(message = "Image can not be empty!!!")
    private MultipartFile file;
}
