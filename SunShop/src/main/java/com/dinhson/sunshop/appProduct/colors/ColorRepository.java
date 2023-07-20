package com.dinhson.sunshop.appProduct.colors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ColorRepository extends JpaRepository<Color, Integer> {

    @Query("SELECT c FROM Color c WHERE c.name = :name")
    Optional<Color> findColorByName(String name);

    @Query("SELECT c FROM Color c")
    List<Color> getAllColor();

    @Query("SELECT c FROM Color c")
    Page<Color> getAllColor(Pageable pageable);

    @Query("SELECT c FROM Color c WHERE c.name LIKE %:name%")
    Page<Color> searchByName(String name, Pageable pageable);
}
