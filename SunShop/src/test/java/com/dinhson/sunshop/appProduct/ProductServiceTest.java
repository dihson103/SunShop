package com.dinhson.sunshop.appProduct;

import com.dinhson.sunshop.appProduct.categories.Category;
import com.dinhson.sunshop.appProduct.categories.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void addNewProduct() {

        Optional<Category> categoryOptional = categoryRepository.findById(1);
        Category category = categoryOptional.orElseThrow(() -> new IllegalArgumentException());
        Product product = new Product();
        product.setName("Product 01");
        product.setDescription("Product 01 description");
        product.setDelete(false);
        product.setCategory(category);
        product.setCreateDate(LocalDateTime.now());
        product.setPrice(29.0);

        Product product1 = new Product();
        product1.setName("Product 02");
        product1.setDescription("Product 02 description");
        product1.setDelete(false);
        product1.setCategory(category);
        product1.setCreateDate(LocalDateTime.now());
        product1.setPrice(39.0);

        productService.addNewProduct(product);
        productService.addNewProduct(product1);
    }
}