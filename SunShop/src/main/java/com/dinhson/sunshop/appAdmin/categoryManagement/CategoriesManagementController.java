package com.dinhson.sunshop.appAdmin.categoryManagement;

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

    @GetMapping
    public String categoriesManagement (@RequestParam(required = false) String searchName, Model model){

        List<CategoryDTO> categories = categoryService.getAllListCategoryDTO();

        model.addAttribute("categories", categories);
        model.addAttribute("searchName", searchName);
        model.addAttribute("categoryRequestDTO", new CategoryRequestDTO());
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

}
