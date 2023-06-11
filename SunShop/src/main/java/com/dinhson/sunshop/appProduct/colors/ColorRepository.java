package com.dinhson.sunshop.appProduct.colors;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ColorRepository extends CrudRepository<Color, Integer> {

    @Query("SELECT c FROM Color c WHERE c.name = :name")
    public Optional<Color> findColorByName(String name);
}
