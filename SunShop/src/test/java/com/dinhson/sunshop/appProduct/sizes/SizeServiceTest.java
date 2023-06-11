package com.dinhson.sunshop.appProduct.sizes;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SizeServiceTest {

    @Test
    void addNewSize() {
        Size size1 = new Size();
        size1.setSize("Tiny");
        size1.setWeight("30 - 45 kg");
        size1.setHeight("120 - 150 cm");

        Size size2 = new Size();
        size2.setSize("Small");
        size2.setWeight("45 - 60 kg");
        size2.setHeight("150 - 165 cm");

        Size size3 = new Size();
        size3.setSize("Medium");
        size3.setWeight("60 - 70 kg");
        size3.setHeight("165 - 180 cm");

        Size size4 = new Size();
        size4.setSize("Large");
        size4.setWeight("70 - 80 kg");
        size4.setHeight("170 - 190 cm");

    }
}