package com.dinhson.sunshop.appProduct.productDetails;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductDetailRepositoryTest {

    @Autowired
    private ProductDetailRepository underTest;

    @Test
    void searchProductDetailByAll() {
        //after
        boolean isDelete = false;
        int colorId = 1;
        String searchName = "Product";

        //when
        List<ProductDetail> productDetails = underTest.searchProductDetailByAll(isDelete, colorId, searchName);

        //then
        boolean actual = productDetails.stream().anyMatch(p -> {
            return p.getColor().getId() != colorId || p.getProduct().isDelete() != isDelete || !p.getProduct().getName().contains(searchName);
        });
        assertEquals(false, actual);
    }

    @Test
    void searchProductDetailByColorAndIsDelete() {
        //after
        boolean isDelete = false;
        int colorId = 1;

        //when
        List<ProductDetail> productDetails = underTest.searchProductDetailByColorAndIsDelete(colorId, isDelete);

        //then
        boolean actual = productDetails.stream().anyMatch(p -> {
            return p.getColor().getId() != colorId || p.getProduct().isDelete() != isDelete;
        });
        assertEquals(false, actual);
    }

    @Test
    void searchProductDetailByIsDelete() {
        //after
        boolean isDelete = false;

        //when
        List<ProductDetail> productDetails = underTest.searchProductDetailByIsDelete(isDelete);

        //then
        boolean actual = productDetails.stream().anyMatch(p -> {
            return p.getProduct().isDelete() != isDelete;
        });
        assertEquals(false, actual);
    }

    @Test
    void searchProductDetailByIsDeleteAndSearchName() {
        //after
        boolean isDelete = false;
        String searchName = "Product";

        //when
        List<ProductDetail> productDetails = underTest.searchProductDetailByIsDeleteAndSearchName(isDelete, searchName);

        //then
        boolean actual = productDetails.stream().anyMatch(p -> {
            return p.getProduct().isDelete() != isDelete || !p.getProduct().getName().contains(searchName);
        });
        assertEquals(false, actual);
    }

    @Test
    void searchProductDetailByColorAndSearchName() {
        //after
        int colorId = 1;
        String searchName = "Product";

        //when
        List<ProductDetail> productDetails = underTest.searchProductDetailByColorAndSearchName(colorId, searchName);

        //then
        boolean actual = productDetails.stream().anyMatch(p -> {
            return p.getColor().getId() != colorId || !p.getProduct().getName().contains(searchName);
        });
        assertEquals(false, actual);
    }

    @Test
    void searchProductDetailByColor() {
        //after
        int colorId = 1;

        //when
        List<ProductDetail> productDetails = underTest.searchProductDetailByColor(colorId);

        //then
        boolean actual = productDetails.stream().anyMatch(p -> {
            return p.getColor().getId() != colorId;
        });
        assertEquals(false, actual);
    }

    @Test
    void searchProductDetailBySearchName() {
        //after
        String searchName = "Product";

        //when
        List<ProductDetail> productDetails = underTest.searchProductDetailBySearchName(searchName);

        //then
        boolean actual = productDetails.stream().anyMatch(p -> {
            return !p.getProduct().getName().contains(searchName);
        });
        assertEquals(false, actual);
    }

    @Test
    void getAll() {
        //after

        //when
        List<ProductDetail> productDetails = underTest.getAll();

        //then
        assertEquals(false, productDetails.isEmpty());
    }
}