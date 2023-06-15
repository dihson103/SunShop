package com.dinhson.sunshop.appProduct.productDetails;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("product-detail")
public class ProductDetailApiController {

    private final ProductDetailService productDetailService;

    @GetMapping
    public String getNumberProductRemain() {

        return null;
    }
}
