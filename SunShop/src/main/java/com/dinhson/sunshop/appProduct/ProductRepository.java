package com.dinhson.sunshop.appProduct;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.name = :name")
    Optional<Product> findProductByName(String name);

    @Query("select p from Product p where p.isDelete = :isDelete")
    List<Product> getAllProductsByIsDelete(boolean isDelete);

    @Query("select p from Product p where p.id = :productId and p.isDelete = false")
    Optional<Product> findProductActiveById(Integer productId);

    @Query("SELECT p " +
            "FROM Product p " +
            "WHERE p.isDelete = :isDelete AND p.category.id = :categoryId AND p.name LIKE %:searchName%")
    List<Product> searchProductByAll(boolean isDelete, int categoryId, String searchName);

    @Query("SELECT p " +
            "FROM Product p " +
            "WHERE p.isDelete = :isDelete AND p.category.id = :categoryId")
    List<Product> searchProductByCategoryAndIsDelete(boolean isDelete, int categoryId);

    @Query("SELECT p " +
            "FROM Product p " +
            "WHERE p.isDelete = :isDelete AND p.name LIKE %:searchName%")
    List<Product> searchProductByIsDeleteAndSearchName(boolean isDelete, String searchName);

    @Query("SELECT p " +
            "FROM Product p " +
            "WHERE p.isDelete = :isDelete")
    List<Product> searchProductByIsDelete(boolean isDelete);

    @Query("SELECT p " +
            "FROM Product p " +
            "WHERE p.category.id = :categoryId AND p.name LIKE %:searchName%")
    List<Product> searchProductByCategoryAndSearchName(int categoryId, String searchName);

    @Query("SELECT p " +
            "FROM Product p " +
            "WHERE p.category.id = :categoryId")
    List<Product> searchProductByCategory(int categoryId);

    @Query("SELECT p " +
            "FROM Product p " +
            "WHERE p.name LIKE %:searchName%")
    List<Product> searchProductBySearchName(String searchName);

    @Query("SELECT p FROM Product p")
    List<Product> getAll();

}
