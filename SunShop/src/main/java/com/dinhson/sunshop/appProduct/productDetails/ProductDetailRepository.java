package com.dinhson.sunshop.appProduct.productDetails;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductDetailRepository extends CrudRepository<ProductDetail, Integer> {

    @Query("SELECT p " +
            "FROM ProductDetail p " +
            "WHERE p.product.id = :productId and p.color.id = :colorId and p.size.id = :sizeId")
    Optional<ProductDetail> findProductDetailByAll(int productId, int colorId, int sizeId);

    Optional<ProductDetail> getById(Integer id);

    @Query("SELECT p " +
            "FROM ProductDetail p " +
            "WHERE p.color.id = :colorId AND p.product.name LIKE %:searchName AND p.product.isDelete = :isDelete")
    List<ProductDetail> searchProductDetailByAll(boolean isDelete, int colorId, String searchName);

    @Query("SELECT p " +
            "FROM ProductDetail p " +
            "WHERE p.color.id = :colorId AND p.product.isDelete = :isDelete")
    List<ProductDetail> searchProductDetailByColorAndIsDelete(int colorId, boolean isDelete);

    @Query("SELECT p " +
            "FROM ProductDetail p " +
            "WHERE p.product.isDelete = :isDelete")
    List<ProductDetail> searchProductDetailByIsDelete(boolean isDelete);

    @Query("SELECT p " +
            "FROM ProductDetail p " +
            "WHERE p.product.name LIKE %:searchName AND p.product.isDelete = :isDelete")
    List<ProductDetail> searchProductDetailByIsDeleteAndSearchName(boolean isDelete, String searchName);

    @Query("SELECT p " +
            "FROM ProductDetail p " +
            "WHERE p.color.id = :colorId AND p.product.name LIKE %:searchName")
    List<ProductDetail> searchProductDetailByColorAndSearchName(int colorId, String searchName);

    @Query("SELECT p " +
            "FROM ProductDetail p " +
            "WHERE p.color.id = :colorId")
    List<ProductDetail> searchProductDetailByColor(int colorId);

    @Query("SELECT p " +
            "FROM ProductDetail p " +
            "WHERE p.product.name LIKE %:searchName")
    List<ProductDetail> searchProductDetailBySearchName(String searchName);

    @Query("SELECT p FROM ProductDetail p")
    List<ProductDetail> getAll();
}
