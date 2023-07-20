package com.dinhson.sunshop.appProduct;

import com.dinhson.sunshop.appProduct.categories.Category;
import com.dinhson.sunshop.appProduct.categories.CategoryRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService underTest;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @Disabled
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

        underTest.addNewProduct(product);
        underTest.addNewProduct(product1);
    }

    @Test
    void searchProductByAll() {
        //after
        String status = "false";
        int categoryId = 1;
        String searchName = "duct";
        boolean isDelete = Boolean.valueOf(status);

        //when
        List<ProductResponseDTO> list = underTest.searchProducts(status, categoryId, searchName, 0, 2);

        //then
        boolean actual = list.stream().anyMatch(p -> {
            return p.productDTO().isDelete() != isDelete
                    || p.productDTO().category().getId() != categoryId
                    || !p.productDTO().name().contains(searchName);
        });
        assertFalse(actual);
    }

    @Test
    void searchProductByCategoryAndIsDelete() {
        //after
        String status = "false";
        int categoryId = 1;
        String searchName = "";
        boolean isDelete = Boolean.valueOf(status);

        //when
        List<ProductResponseDTO> list = underTest.searchProducts(status, categoryId, searchName, 0, 2);

        //then
        boolean actual = list.stream().anyMatch(p -> {
            return p.productDTO().isDelete() != isDelete
                    || p.productDTO().category().getId() != categoryId;
        });
        assertFalse(actual);
    }

    @Test
    void searchProductByIsDeleteAndSearchName() {
        //after
        String status = "false";
        int categoryId = 0;
        String searchName = "duct";
        boolean isDelete = Boolean.valueOf(status);

        //when
        List<ProductResponseDTO> list = underTest.searchProducts(status, categoryId, searchName, 0, 2);

        //then
        boolean actual = list.stream().anyMatch(p -> {
            return p.productDTO().isDelete() != isDelete
                    || !p.productDTO().name().contains(searchName);
        });
        assertFalse(actual);
    }

    @Test
    void searchProductByIsDelete() {
        //after
        String status = "false";
        int categoryId = 0;
        String searchName = "";
        boolean isDelete = Boolean.valueOf(status);

        //when
        List<ProductResponseDTO> list = underTest.searchProducts(status, categoryId, searchName, 0, 2);

        //then
        boolean actual = list.stream().anyMatch(p -> {
            return p.productDTO().isDelete() != isDelete;
        });
        assertFalse(actual);
    }

    @Test
    void searchProductByCategoryAndSearchName() {
        //after
        String status = "all";
        int categoryId = 1;
        String searchName = "duct";

        //when
        List<ProductResponseDTO> list = underTest.searchProducts(status, categoryId, searchName, 0, 2);

        //then
        boolean actual = list.stream().anyMatch(p -> {
            return p.productDTO().category().getId() != categoryId
                    || !p.productDTO().name().contains(searchName);
        });
        assertFalse(actual);
    }

    @Test
    void searchProductByCategory() {
        //after
        String status = "all";
        int categoryId = 1;
        String searchName = "";

        //when
        List<ProductResponseDTO> list = underTest.searchProducts(status, categoryId, searchName, 0, 2);

        //then
        boolean actual = list.stream().anyMatch(p -> {
            return p.productDTO().category().getId() != categoryId;
        });
        assertFalse(actual);
    }

    @Test
    void searchProductBySearchName() {
        //after
        String status = "all";
        int categoryId = 0;
        String searchName = "son";
        boolean isDelete = Boolean.valueOf(status);

        //when
        List<ProductResponseDTO> list = underTest.searchProducts(status, categoryId, searchName, 0, 2);

        //then
        boolean actual = list.stream().anyMatch(p -> {
            return !p.productDTO().name().contains(searchName);
        });

        list.forEach(System.out::println);
        assertFalse(actual);
    }

    @Test
    void getAll() {
        //after
        String status = "all";
        int categoryId = 0;
        String searchName = "";

        //when
        List<ProductResponseDTO> list = underTest.searchProducts(status, categoryId, searchName, 0, 2);

        //then
        boolean actual = list.isEmpty();
        assertFalse(actual);
    }

}