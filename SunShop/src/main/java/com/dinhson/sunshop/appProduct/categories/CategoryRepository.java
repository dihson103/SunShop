package com.dinhson.sunshop.appProduct.categories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

    @Query("SELECT c FROM Category c WHERE c.name = :name")
    Optional<Category> findCategoryByName(String name);

    @Query("SELECT c FROM Category c")
    List<Category> getAll();
}
