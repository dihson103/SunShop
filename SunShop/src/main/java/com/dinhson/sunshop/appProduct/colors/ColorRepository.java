package com.dinhson.sunshop.appProduct.colors;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ColorRepository extends CrudRepository<Color, Integer> {

    @Query("SELECT c FROM Color c WHERE c.name = :name")
    Optional<Color> findColorByName(String name);

    @Query("SELECT c FROM Color c")
    List<Color> getAllColor();

    @Query("SELECT c FROM Color c WHERE c.name LIKE %:name%")
    List<Color> searchByName(String name);
}
