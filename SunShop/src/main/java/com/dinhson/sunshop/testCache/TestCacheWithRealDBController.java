package com.dinhson.sunshop.testCache;

import com.dinhson.sunshop.appProduct.categories.Category;
import com.dinhson.sunshop.appProduct.categories.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@EnableCaching
@RequestMapping("categories")
public class TestCacheWithRealDBController {

    private final CategoryService categoryService;

    @GetMapping
    public Iterable<Category> getAll(){
        return categoryService.findAllCategory();
    }

    @PostMapping
    public Category add(@RequestBody Category category){
        return categoryService.addNewCategory(category);
    }
}
