package com.dinhson.sunshop.appProduct;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository underTest;

    @Test
    void searchProductByAll() {
        //after
        boolean isDelete = false;
        int categoryId = 1;
        String searchName = "Product";

        //when
        List<Product> list = underTest.searchProductByAll(isDelete, categoryId, searchName);

        //then
        boolean actual = list.stream().anyMatch(p -> {
           return p.isDelete() != isDelete || p.getCategory().getId() != categoryId || !p.getName().contains(searchName);
        });
        assertFalse(actual);
    }

    @Test
    void searchProductByCategoryAndIsDelete() {
        //after
        boolean isDelete = false;
        int categoryId = 1;

        //when
        List<Product> list = underTest.searchProductByCategoryAndIsDelete(isDelete, categoryId);

        //then
        boolean actual = list.stream().anyMatch(p -> {
            return p.isDelete() != isDelete || p.getCategory().getId() != categoryId;
        });
        assertFalse(actual);
    }

    @Test
    void searchProductByIsDeleteAndSearchName() {
        //after
        boolean isDelete = false;
        String searchName = "Product";

        //when
        List<Product> list = underTest.searchProductByIsDeleteAndSearchName(isDelete, searchName);

        //then
        boolean actual = list.stream().anyMatch(p -> {
            return p.isDelete() != isDelete || !p.getName().contains(searchName);
        });
        assertFalse(actual);
    }

    @Test
    void searchProductByIsDelete() {
        //after
        boolean isDelete = false;

        //when
        List<Product> list = underTest.searchProductByIsDelete(isDelete);

        //then
        boolean actual = list.stream().anyMatch(p -> {
            return p.isDelete() != isDelete;
        });
        assertFalse(actual);
    }

    @Test
    void searchProductByCategoryAndSearchName() {
        //after
        int categoryId = 1;
        String searchName = "Product";

        //when
        List<Product> list = underTest.searchProductByCategoryAndSearchName(categoryId, searchName);

        //then
        boolean actual = list.stream().anyMatch(p -> {
            return p.getCategory().getId() != categoryId || !p.getName().contains(searchName);
        });
        assertFalse(actual);
    }

    @Test
    void searchProductByCategory() {
        //after
        int categoryId = 1;

        //when
        List<Product> list = underTest.searchProductByCategory(categoryId);

        //then
        boolean actual = list.stream().anyMatch(p -> {
            return p.getCategory().getId() != categoryId;
        });
        assertFalse(actual);
    }

    @Test
    void searchProductBySearchName() {
        //after
        String searchName = "Product";

        //when
        List<Product> list = underTest.searchProductBySearchName(searchName);

        //then
        boolean actual = list.stream().anyMatch(p -> {
            return !p.getName().contains(searchName);
        });

        list.forEach(System.out::println);
        assertFalse(actual);
    }

    @Test
    void getAll() {
        //after

        //when
        List<Product> list = underTest.getAll();

        //then
        assertFalse(list.isEmpty());
    }
}