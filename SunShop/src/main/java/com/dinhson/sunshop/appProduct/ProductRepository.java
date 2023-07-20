package com.dinhson.sunshop.appProduct;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.name = :name")
    Optional<Product> findProductByName(String name);

    @Query("select p from Product p where p.isDelete = :isDelete")
    List<Product> getAllProductsByIsDelete(boolean isDelete);

    @Query("select p from Product p where p.id = :productId and p.isDelete = false")
    Optional<Product> findProductActiveById(Integer productId);

    @Query("SELECT p " +
            "FROM Product p " +
            "WHERE p.isDelete = :isDelete AND p.category.id = :categoryId AND p.name LIKE %:searchName%")
    Page<Product> searchProductByAll(boolean isDelete, int categoryId, String searchName, Pageable pageable);

    @Query("SELECT p " +
            "FROM Product p " +
            "WHERE p.isDelete = :isDelete AND p.category.id = :categoryId")
    Page<Product> searchProductByCategoryAndIsDelete(boolean isDelete, int categoryId, Pageable pageable);

    @Query("SELECT p " +
            "FROM Product p " +
            "WHERE p.isDelete = :isDelete AND p.name LIKE %:searchName%")
    Page<Product> searchProductByIsDeleteAndSearchName(boolean isDelete, String searchName, Pageable pageable);

    @Query("SELECT p " +
            "FROM Product p " +
            "WHERE p.isDelete = :isDelete")
    Page<Product> searchProductByIsDelete(boolean isDelete, Pageable pageable);

    @Query("SELECT p " +
            "FROM Product p " +
            "WHERE p.category.id = :categoryId AND p.name LIKE %:searchName%")
    Page<Product> searchProductByCategoryAndSearchName(int categoryId, String searchName, Pageable pageable);

    @Query("SELECT p " +
            "FROM Product p " +
            "WHERE p.category.id = :categoryId")
    Page<Product> searchProductByCategory(int categoryId, Pageable pageable);

    @Query("SELECT p " +
            "FROM Product p " +
            "WHERE p.name LIKE %:searchName%")
    Page<Product> searchProductBySearchName(String searchName, Pageable pageable);

    @Query("SELECT p FROM Product p")
    Page<Product> getAll(Pageable pageable);

    @Query("SELECT COUNT(p) FROM Product p WHERE p.category.id = :categoryId")
    Integer getNumberProductByCategoryId(Integer categoryId);

}
