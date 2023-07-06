package com.dinhson.sunshop.appProduct;

import com.dinhson.sunshop.appProduct.categories.Category;
import com.dinhson.sunshop.appProduct.images.Image;
import lombok.Builder;

import java.util.Set;
@Builder
public record ProductDTO(
        int id,
        String name,
        Double price,
        String description,
        String img,
        Set<Image> images,
        boolean isDelete,
        Category category

        //TODO them discount
) {
}
