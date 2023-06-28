package com.dinhson.sunshop.appAdmin.productManagement;

import com.dinhson.sunshop.appProduct.ProductResponseDTO;
import com.dinhson.sunshop.appProduct.ProductService;
import com.dinhson.sunshop.appProduct.categories.Category;
import com.dinhson.sunshop.appProduct.categories.CategoryService;
import com.dinhson.sunshop.appProduct.colors.Color;
import com.dinhson.sunshop.appProduct.colors.ColorService;
import com.dinhson.sunshop.appProduct.sizes.Size;
import com.dinhson.sunshop.appProduct.sizes.SizeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("admin/products")
@EnableCaching
public class ProductsManagementController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final SizeService sizeService;
    private final ColorService colorService;

    @GetMapping()
    public String getProducts(@RequestParam(defaultValue = "all") String isDelete,
                              @RequestParam(defaultValue = "") String searchName,
                              @RequestParam(defaultValue = "0") Integer categoryId,
                              Model model){
        List<ProductResponseDTO> productResponseDTOS = productService.searchProducts(isDelete, categoryId, searchName);
        Iterable<Category> categories = categoryService.findAllCategory();

        model.addAttribute("categories", categories);
        model.addAttribute("products", productResponseDTOS);
        model.addAttribute("isDelete", isDelete);
        model.addAttribute("searchName", searchName);
        model.addAttribute("categoryId", categoryId);
        return "admin-products-management";
    }

    @GetMapping("create")
    public String createProductForm(Model model){
        Iterable<Category> categories = categoryService.findAllCategory();

        model.addAttribute("categories", categories);
        model.addAttribute("product", new ProductRequestCreate());
        return "admin-create-product";
    }

    @PostMapping(value = "create", consumes = { "multipart/form-data" })
    public String createProduct(@ModelAttribute("product") @Valid ProductRequestCreate product,
                                BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new IllegalArgumentException(bindingResult.toString());
        }
        Category category = categoryService.getCategoryById(product.getCategoryId());
        productService.createNewProduct(product, category);
        return "redirect:/admin/products";
    }

    @GetMapping("{productId}")
    public String getProductDetail(@PathVariable Integer productId,
                                   Model model){
        ProductRequestUpdate productRequestUpdate = productService.findProductRequestUpdateById(productId);
        Iterable<Category> categories = categoryService.findAllCategory();
        Iterable<Color> colors = colorService.findAllColor();
        Iterable<Size> sizes = sizeService.findAllSize();

        model.addAttribute("colors", colors);
        model.addAttribute("sizes", sizes);
        model.addAttribute("categories", categories);
        model.addAttribute("product", productRequestUpdate);
        return "admin-view-product-details";
    }

    @PostMapping( value = "update", consumes = { "multipart/form-data" })
    public String updateProduct(@ModelAttribute @Valid ProductRequestUpdate product,
                                RedirectAttributes redirectAttributes){
        Category category = categoryService.getCategoryById(product.getCategoryId());
        productService.updateProduct(product, category);
        redirectAttributes.addFlashAttribute("message", "Update success!!!");
        return "redirect:/admin/products/" + product.getId();
    }
}
