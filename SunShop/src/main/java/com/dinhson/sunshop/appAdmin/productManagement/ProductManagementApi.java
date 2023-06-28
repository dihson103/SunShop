package com.dinhson.sunshop.appAdmin.productManagement;

import com.dinhson.sunshop.appProduct.ProductService;
import com.dinhson.sunshop.appProduct.categories.Category;
import com.dinhson.sunshop.appProduct.categories.CategoryService;
import com.dinhson.sunshop.appProduct.productDetails.ProductDetailService;
import com.dinhson.sunshop.common.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("admin/api/products")
public class ProductManagementApi {

    private final ProductService productService;
    private final ProductDetailService productDetailService;
    
    @PutMapping(value = "changeStatus")
    public ApiResponse updateStatus(@RequestParam Integer productId){
        productService.changeStatus(productId);
        return new ApiResponse("Update success!!!", HttpStatus.OK);
    }

    @GetMapping("quantity")
    public NumberProductResponseDTO getNumberProductRemain(@RequestParam Integer sizeId,
                                                           @RequestParam Integer colorId,
                                                           @RequestParam Integer productId){
        return productDetailService.getNumberProductRemain(sizeId, colorId, productId);
    }

    @PostMapping("quantity")
    public ApiResponse updateQuantity(@RequestBody @Valid UpdateNumberProductDTO updateNumberProductDTO){
        return productDetailService.updateProductQuantity(updateNumberProductDTO);
    }

}
