package com.dinhson.sunshop.appProduct.sizes;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SizeRepository extends CrudRepository<Size, Integer> {

    @Query("SELECT s FROM Size s WHERE s.size = :size")
    Optional<Size> findSizeBySize(String size);

}
