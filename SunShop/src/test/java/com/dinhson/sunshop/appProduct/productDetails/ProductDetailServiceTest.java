package com.dinhson.sunshop.appProduct.productDetails;

import com.dinhson.sunshop.appProduct.Product;
import com.dinhson.sunshop.appProduct.ProductRepository;
import com.dinhson.sunshop.appProduct.ProductResponseDTO;
import com.dinhson.sunshop.appProduct.colors.Color;
import com.dinhson.sunshop.appProduct.colors.ColorRepository;
import com.dinhson.sunshop.appProduct.sizes.Size;
import com.dinhson.sunshop.appProduct.sizes.SizeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProductDetailServiceTest {


    @Autowired
    private ProductDetailService underTest;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void addNewProductDetail() {

        Optional<Color> colorOptional1 = colorRepository.findById(2);
        Color color = colorOptional1
                .orElseThrow(() -> new IllegalArgumentException("Color not found!!"));

        Optional<Size> sizeOptional1 = sizeRepository.findById(2);
        Size size = sizeOptional1
                .orElseThrow(() -> new IllegalArgumentException("Size is not found!!!"));

        Optional<Product> productOptional1 = productRepository.findById(1);
        Product product = productOptional1
                .orElseThrow(() -> new IllegalArgumentException("Product is not found!!!"));

        ProductDetail productD1 = new ProductDetail();
        productD1.setProduct(product);
        productD1.setSize(size);
        productD1.setColor(color);
        productD1.setNumber(5);

        underTest.addNewProductDetail(productD1);
    }

}