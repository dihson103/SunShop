package com.dinhson.sunshop.appProduct;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductService productService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> searchProduct(@RequestParam Integer categoryId,
                                          @RequestParam Integer colorId,
                                          @RequestParam Integer sizeId,
                                          @RequestParam Double minPrice,
                                          @RequestParam Double maxPrice){
        List<ProductDTO> productDTOS = productService.filterProducts(categoryId, minPrice, maxPrice, colorId, sizeId);
        return productDTOS;
    }
}
