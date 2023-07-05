package com.dinhson.sunshop.appCart;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartServiceTest {

    @Autowired
    private CartService underTest;

    @Autowired
    private CartRepository cartRepository;

    @Test
    @Disabled
    void canAddItemsNotExistInCartToCart() {

        //after
        int userId = 4;
        int productDetailId = 4;
        int number = cartRepository.countNumberItemInCart(userId);
        int numberProduct = 2;

        //when
        underTest.addItemsToCart(productDetailId, userId, numberProduct);

        //then
        int numberAfterAdd = cartRepository.countNumberItemInCart(userId);
        assertTrue(numberAfterAdd > number);
    }

    @Test
    @Disabled
    void canAddItemsExistInCartToCart() {

        //after
        int userId = 1;
        int productDetailId = 4;
        int number = cartRepository.countNumberItemInCart(userId);
        int quantity = cartRepository.findCartItemByProductDetailId(productDetailId, userId).get().getQuantity();
        int numberProduct = 2;

        //when
        underTest.addItemsToCart(productDetailId, userId, numberProduct);

        //then
        int numberBeforeAdd = cartRepository.countNumberItemInCart(userId);
        int quantityBeforeAdd = cartRepository.findCartItemByProductDetailId(productDetailId, userId).get().getQuantity();

        assertTrue(numberBeforeAdd == number);
        assertTrue(quantityBeforeAdd > quantity);
    }

    @Test
    @Disabled
    void canDeleteItem() {

        //after
        int userId = 1;
        int productDetailId = 4;
        Optional<CartItem> after = cartRepository.findCartItemByProductDetailId(productDetailId, userId);

        //when
        underTest.deleteItem(productDetailId, userId);

        //then
        Optional<CartItem> before = cartRepository.findCartItemByProductDetailId(productDetailId, userId);

        assertNotEquals(after.isPresent(), before.isPresent());
    }

    @Test
    @Disabled
    void canChangeQuantityItem() {

        //after
        int userId = 1;
        int productDetailId = 3;
        int number = 1;
        int quantityAfter = cartRepository.findCartItemByProductDetailId(productDetailId, userId).get().getQuantity();

        //when
        underTest.changeQuantityItem(productDetailId, userId, number);

        //then
        int quantityBefore = cartRepository.findCartItemByProductDetailId(productDetailId, userId).get().getQuantity();

        assertEquals((quantityAfter + number), quantityBefore);
    }

    @Test
    @Disabled
    void canNotChangeQuantityItemOutOfNumberRemain() {

        //after
        int userId = 1;
        int productDetailId = 3;
        int number = 10;
        String expected = "Number is more than number product remain or less than 1!!!";

        //when
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            underTest.changeQuantityItem(productDetailId, userId, number);
        });
        String actual = exception.getMessage();

        //then
        assertEquals(expected, actual);
    }

}