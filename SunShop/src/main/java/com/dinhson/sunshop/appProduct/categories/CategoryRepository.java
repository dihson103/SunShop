package com.dinhson.sunshop.appProduct.categories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

    @Query("SELECT c FROM Category c WHERE c.name = :name")
    public Optional<Category> findCategoryByName(String name);
}
