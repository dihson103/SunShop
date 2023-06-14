package com.dinhson.sunshop.appProduct;

import com.dinhson.sunshop.appProduct.categories.Category;
import com.dinhson.sunshop.appProduct.categories.CategoryService;
import com.dinhson.sunshop.appProduct.colors.Color;
import com.dinhson.sunshop.appProduct.colors.ColorService;
import com.dinhson.sunshop.appProduct.productDetails.ProductDetailDTO;
import com.dinhson.sunshop.appProduct.sizes.Size;
import com.dinhson.sunshop.appProduct.sizes.SizeService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
@EnableCaching
public class ProductController {

    private final ProductService productService;
    private final SizeService sizeService;
    private final ColorService colorService;
    private final CategoryService categoryService;

    @GetMapping("shop")
    public String listProduct(Model model){

        System.out.println("access");

        List<ProductDTO> productDTOS = productService.findAllProductActive();
        Iterable<Size> sizes = sizeService.findAllSize();
        Iterable<Color> colors = colorService.findAllColor();
        Iterable<Category> categories = categoryService.findAllCategory();

        model.addAttribute("products", productDTOS);
        model.addAttribute("categories", categories);
        model.addAttribute("colors", colors);
        model.addAttribute("sizes", sizes);
        return "shop-grid";
    }

    @GetMapping("/product")
    public String getProduct(@RequestParam Integer productId, Model model){

        ProductDetailDTO productDetailDTO = productService.findProductActiveById(productId);

        model.addAttribute("product", productDetailDTO);
        return "shopDetails";
    }
}
