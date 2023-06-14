package com.dinhson.sunshop.appProduct.colors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ColorServiceTest {

    @Autowired
    private ColorService colorService;

    @Test
    void addNewColor() {

        Color color1 = new Color();
        color1.setName("White");

        Color color2 = new Color();
        color2.setName("Black");

        Color color3 = new Color();
        color3.setName("Green");

        Color color4 = new Color();
        color4.setName("Blue");

        colorService.addNewColor(color1);
        colorService.addNewColor(color2);
        colorService.addNewColor(color3);
        colorService.addNewColor(color4);

    }
}