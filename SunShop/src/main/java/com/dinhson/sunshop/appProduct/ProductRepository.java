package com.dinhson.sunshop.appProduct;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.name = :name")
    public Optional<Product> findProductByName(String name);

    @Query("select p from Product p where p.isDelete = :isDelete")
    public List<Product> getAllProductsByIsDelete(boolean isDelete);

    @Query("select p from Product p where p.id = :productId and p.isDelete = false")
    public Optional<Product> findProductActiveById(Integer productId);
}
