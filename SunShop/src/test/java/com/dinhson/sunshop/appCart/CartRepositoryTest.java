package com.dinhson.sunshop.appCart;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CartRepositoryTest {

    @Autowired
    private CartRepository underTest;

    @Test
    void findCartItemByProductDetailId() {
    }

    @Test
    void countNumberItemInCart() {
    }

    @Test
    void findCartByUserId() {
    }
}