package com.dinhson.sunshop.appProduct.categories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("SELECT c FROM Category c WHERE c.name = :name")
    Optional<Category> findCategoryByName(String name);

    @Query("SELECT c FROM Category c")
    Page<Category> getAll(Pageable pageable);

    @Query("SELECT c FROM Category c")
    List<Category> getAll();
}
