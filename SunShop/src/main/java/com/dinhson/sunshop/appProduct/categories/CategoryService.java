package com.dinhson.sunshop.appProduct.categories;

import com.dinhson.sunshop.appAdmin.categoryManagement.CategoryDTO;
import com.dinhson.sunshop.appAdmin.categoryManagement.CategoryDTOMapper;
import com.dinhson.sunshop.appAdmin.categoryManagement.CategoryRequestDTO;
import com.dinhson.sunshop.exception.CategoryAlreadyExistException;
import com.dinhson.sunshop.utils.FileUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryDTOMapper categoryDTOMapper;
    private static Integer totalPages = 0;

    public Category addNewCategory(Category category) {
        if (isCategoryExist(category)) {
            throw new CategoryAlreadyExistException("Category " + category.getName() + " is already exist!!!");
        }
        return categoryRepository.save(category);
    }

    private boolean isCategoryExist(Category category) {
        return categoryRepository.findCategoryByName(category.getName()).isPresent();
    }

    public Page<Category> findAllCategory(Integer pageIndex, Integer pageSize) {
        return categoryRepository.getAll(PageRequest.of(pageIndex, pageSize));
    }

    public List<Category> findAllCategory() {
        return categoryRepository.getAll();
    }

    public Category getCategoryById(Integer categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Can not found category!!!"));
    }

    public List<CategoryDTO> getAllListCategoryDTO(Integer pageIndex, Integer pageSize){
        Page<Category> categoryPage = findAllCategory(pageIndex, pageSize);
        totalPages = categoryPage.getTotalPages();

        return categoryPage.stream()
                .map(categoryDTOMapper)
                .collect(Collectors.toList());
    }

    public void createCategory(CategoryRequestDTO categoryRequestDTO){
        String image = FileUtils.getImageUrl(categoryRequestDTO.getFile());
        Category category = Category.builder()
                .name(categoryRequestDTO.getName())
                .img(image)
                .build();
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

        if(!categoryRequestDTO.getFile().isEmpty()){
            String image = FileUtils.getImageUrl(categoryRequestDTO.getFile());
            category.setImg(image);
        }

        categoryRepository.save(category);
    }

    public void deleteCategory(Integer categoryId){
        Category category = getCategoryById(categoryId);
        categoryRepository.delete(category);
    }

    public Integer getTotalPages() {
        return totalPages;
    }
}
