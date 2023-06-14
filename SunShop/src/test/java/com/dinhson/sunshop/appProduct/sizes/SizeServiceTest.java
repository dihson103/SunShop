package com.dinhson.sunshop.appProduct.sizes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SizeServiceTest {

    @Autowired
    private SizeService underTest;

    @Test
    void canAddNewSize() {
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

        underTest.addNewSize(size1);
        underTest.addNewSize(size2);
        underTest.addNewSize(size3);
        underTest.addNewSize(size4);
    }
}