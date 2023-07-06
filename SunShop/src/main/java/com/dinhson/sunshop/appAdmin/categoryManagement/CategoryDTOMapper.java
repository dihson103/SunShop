package com.dinhson.sunshop.appAdmin.categoryManagement;

import com.dinhson.sunshop.appProduct.ProductService;
import com.dinhson.sunshop.appProduct.categories.Category;
import com.dinhson.sunshop.appProduct.productDetails.ProductDetailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@AllArgsConstructor
public class CategoryDTOMapper implements Function<Category, CategoryDTO> {

    private final ProductService productService;
    private final ProductDetailService productDetailService;

    @Override
    public CategoryDTO apply(Category category) {
        Integer number = productDetailService.getNumberProductRemainByCategoryId(category.getId());

        return CategoryDTO
                .builder()
                .id(category.getId())
                .name(category.getName())
                .img(category.getImg())
                .number(productService.getNumberProductByCategoryId(category.getId()))
                .numberRemain(number == null ? 0 : number)
                .build();
    }
}
