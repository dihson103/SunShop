package com.dinhson.sunshop.appAdmin;

import com.dinhson.sunshop.appProduct.ProductResponseDTO;
import com.dinhson.sunshop.appProduct.ProductService;
import com.dinhson.sunshop.appProduct.categories.Category;
import com.dinhson.sunshop.appProduct.categories.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("admin/products")
@EnableCaching
public class ProductsManagementController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping()
    public String getProducts(@RequestParam(defaultValue = "all") String isDelete,
                              @RequestParam(defaultValue = "") String searchName,
                              @RequestParam(defaultValue = "0") Integer categoryId,
                              Model model){
        List<ProductResponseDTO> productResponseDTOS = productService.searchProducts(isDelete, categoryId, searchName);
        Iterable<Category> categories = categoryService.findAllCategory();

        model.addAttribute("categories", categories);
        model.addAttribute("products", productResponseDTOS);
        return "admin-products-management";
    }
}
