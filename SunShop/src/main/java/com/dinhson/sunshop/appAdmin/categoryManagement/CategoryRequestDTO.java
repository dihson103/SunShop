package com.dinhson.sunshop.appAdmin.categoryManagement;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequestDTO {

    private Integer id;

    @NotNull(message = "Category's name can not be empty!!!")
    private String name;

    @NotNull(message = "Category's image can not be empty!!!")
    private MultipartFile file;

}
