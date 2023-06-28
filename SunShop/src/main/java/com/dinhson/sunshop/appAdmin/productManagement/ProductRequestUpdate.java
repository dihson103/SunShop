package com.dinhson.sunshop.appAdmin.productManagement;

import com.dinhson.sunshop.appProduct.Product;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestUpdate {

    @NotNull(message = "Product id can not be empty!!!")
    private Integer id;

    @NotNull(message = "Product name must not be empty!!")
    private String name;

    @NotNull(message = "Category must not be empty")
    private Integer categoryId;

    @NotNull(message = "Price must not be empty")
    private Double price;

    @NotNull(message = "Description must not be empty")
    private String description;

    private MultipartFile file;

    private Boolean isDelete;

    public ProductRequestUpdate (Product product){
        id = product.getId();
        name = product.getName();
        categoryId = product.getCategory().getId();
        price = product.getPrice();
        description = product.getDescription();
        isDelete = product.isDelete();
    }
}
