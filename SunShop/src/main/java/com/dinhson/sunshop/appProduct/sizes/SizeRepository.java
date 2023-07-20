package com.dinhson.sunshop.appProduct.sizes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SizeRepository extends JpaRepository<Size, Integer> {

    @Query("SELECT s FROM Size s WHERE s.size = :size")
    Optional<Size> findSizeBySize(String size);

    @Query("SELECT s FROM Size s")
    Page<Size> getAll(Pageable pageable);

}
