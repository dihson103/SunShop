package com.dinhson.sunshop.appProduct.categories;

import com.dinhson.sunshop.exception.CategoryAlreadyExistException;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @CacheEvict(value = "categories", allEntries = true)
    public Category addNewCategory(Category category) {
        if (isCategoryExist(category)) {
            throw new CategoryAlreadyExistException("Category " + category.getName() + " is already exist!!!");
        }
        return categoryRepository.save(category);
    }

    private boolean isCategoryExist(Category category) {
        return categoryRepository.findCategoryByName(category.getName()).isPresent();
    }

    @Cacheable("categories")
    public Iterable<Category> findAllCategory() {
        System.out.println("get all categories");
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Integer categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Can not found category!!!"));
    }
}
