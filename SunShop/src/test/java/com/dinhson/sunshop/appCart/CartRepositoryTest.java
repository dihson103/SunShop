package com.dinhson.sunshop.appCart;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartRepositoryTest {

    @Autowired
    private CartRepository underTest;

    @Test
    void canFindCartItemByProductDetailId() {
        //after
        int productDetailId = 1;
        int userId = 1;

        //when
        Optional<CartItem> cartItem = underTest.findCartItemByProductDetailId(productDetailId, userId);

        //then
        assertEquals(true, cartItem.isPresent());
    }

    @Test
    void canNotFindCartItemByProductDetailId() {
        //after
        int productDetailId = 10;
        int userId = 1;

        //when
        Optional<CartItem> cartItem = underTest.findCartItemByProductDetailId(productDetailId, userId);

        //then
        assertNotEquals(true, cartItem.isPresent());
    }

    @Test
    void countNumberItemInCart() {

        //after
        int userId = 1;
        int expected = 3;

        //when
        int actual = underTest.countNumberItemInCart(userId);

        //then
        assertEquals(expected, actual);

    }

    @Test
    void canFindCartByUserId() {

        //after
        int userId = 1;

        //when
        List<CartItem> actual = underTest.findCartByUserId(userId);

        //then
        assertEquals(false, actual.isEmpty());
    }

    @Test
    void canNotFindCartByUserId() {

        //after
        int userId = 10;

        //when
        List<CartItem> actual = underTest.findCartByUserId(userId);

        //then
        assertNotEquals(false, actual.isEmpty());
    }
}