package com.dinhson.sunshop.appAdmin.categoryManagement;

import com.dinhson.sunshop.appProduct.ProductService;
import com.dinhson.sunshop.appProduct.categories.Category;
import com.dinhson.sunshop.appProduct.categories.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("admin/categories")
public class CategoriesManagementController {

    private final CategoryService categoryService;
    private final ProductService productService;

    @GetMapping
    public String categoriesManagement (@RequestParam(required = false) String searchName,
                                        @RequestParam(required = false, defaultValue = "0") Integer pageIndex,
                                        @RequestParam(required = false, defaultValue = "2") Integer pageSize,
                                        Model model){

        List<CategoryDTO> categories = categoryService.getAllListCategoryDTO(pageIndex, pageSize);
        Integer totalPages = categoryService.getTotalPages();

        model.addAttribute("categories", categories);
        model.addAttribute("searchName", searchName);
        model.addAttribute("categoryRequestDTO", new CategoryRequestDTO());
        model.addAttribute("pageIndex", pageIndex);
        model.addAttribute("totalPages", totalPages);
        return "admin-categories-management";
    }

    @PostMapping(consumes = { "multipart/form-data" })
    public String addCategory (@ModelAttribute @Valid CategoryRequestDTO categoryRequestDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            throw new IllegalArgumentException(bindingResult.toString());
        }

        categoryService.createCategory(categoryRequestDTO);
        redirectAttributes.addFlashAttribute("message", "Add new category success!!!");
        return "redirect:/admin/categories";
    }

    @PostMapping(value = "update", consumes = { "multipart/form-data" })
    public String updateCategory (@ModelAttribute CategoryRequestDTO categoryRequestDTO,
                                  RedirectAttributes redirectAttributes){

        categoryService.updateCategory(categoryRequestDTO);
        redirectAttributes.addFlashAttribute("message", "Update category success!!!");
        return "redirect:/admin/categories";
    }

    @PostMapping("delete")
    public String deleteCategory(@RequestParam Integer categoryId, RedirectAttributes redirectAttributes){
        String message;
        if(productService.getNumberProductByCategoryId(categoryId) > 0){
            message = "This category was used, can not delete this!!!";
        }
        else {
            categoryService.deleteCategory(categoryId);
            message = "Delete category success!!!";
        }
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/admin/categories";
    }

}
