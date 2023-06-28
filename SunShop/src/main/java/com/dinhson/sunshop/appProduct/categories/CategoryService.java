package com.dinhson.sunshop.appProduct.categories;

import com.dinhson.sunshop.appAdmin.categoryManagement.CategoryDTO;
import com.dinhson.sunshop.appAdmin.categoryManagement.CategoryDTOMapper;
import com.dinhson.sunshop.appAdmin.categoryManagement.CategoryRequestDTO;
import com.dinhson.sunshop.exception.CategoryAlreadyExistException;
import com.dinhson.sunshop.utils.FileUtils;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryDTOMapper categoryDTOMapper;

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
    public List<Category> findAllCategory() {
        System.out.println("get all categories");
        return categoryRepository.getAll();
    }

    public Category getCategoryById(Integer categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Can not found category!!!"));
    }

    public List<CategoryDTO> getAllListCategoryDTO(){
        return findAllCategory().stream()
                .map(categoryDTOMapper)
                .collect(Collectors.toList());
    }

    public void createCategory(CategoryRequestDTO categoryRequestDTO){
        String image = FileUtils.getImageUrl(categoryRequestDTO.getFile());
        Category category = new Category(categoryRequestDTO.getName(), image);
        addNewCategory(category);
    }

    public void updateCategory(CategoryRequestDTO categoryRequestDTO){
        Category category = getCategoryById(categoryRequestDTO.getId());
        category.setName(categoryRequestDTO.getName());

        if(isCategoryExist(category)){
            throw new CategoryAlreadyExistException("Category " + category.getName() + " is already exist!!!");
        } else if (category.getName().isEmpty()) {
            throw new CategoryAlreadyExistException("Category name can not be empty!!!");
        }

        if(categoryRequestDTO.getFile().isEmpty()){
            String image = FileUtils.getImageUrl(categoryRequestDTO.getFile());
            category.setImg(image);
        }

        categoryRepository.save(category);
    }
}
