package com.dinhson.sunshop.appProduct.categories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    void addNewCategory() {

        Category category1 = new Category();
        category1.setName("Category 01");
        category1.setImg("img/categories/cat-1.jpg");

        Category category2 = new Category();
        category2.setName("Category 02");
        category2.setImg("img/categories/cat-2.jpg");

        Category category3 = new Category();
        category3.setName("Category 03");
        category3.setImg("img/categories/cat-3.jpg");

        Category category4 = new Category();
        category4.setName("Category 04");
        category4.setImg("img/categories/cat-4.jpg");

        Category category5 = new Category();
        category5.setName("Category 05");
        category5.setImg("img/categories/cat-5.jpg");

        categoryService.addNewCategory(category1);
        categoryService.addNewCategory(category2);
        categoryService.addNewCategory(category3);
        categoryService.addNewCategory(category4);
        categoryService.addNewCategory(category5);
    }
}