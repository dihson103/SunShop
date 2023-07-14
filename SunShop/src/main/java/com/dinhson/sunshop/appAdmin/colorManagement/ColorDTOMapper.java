package com.dinhson.sunshop.appAdmin.colorManagement;

import com.dinhson.sunshop.appProduct.colors.Color;
import com.dinhson.sunshop.appProduct.productDetails.ProductDetailService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ColorDTOMapper implements Function<Color, ColorDTO> {

    private final ProductDetailService productDetailService;

    public ColorDTOMapper (@Lazy ProductDetailService productDetailService){
        this.productDetailService = productDetailService;
    }

    @Override
    public ColorDTO apply(Color color) {
        Integer number = productDetailService.getNumberProductRemainByColorId(color.getId());

        return ColorDTO
                .builder()
                .id(color.getId())
                .name(color.getName())
                .numberRemain(number == null ? 0 : number)
                .build();
    }
}
