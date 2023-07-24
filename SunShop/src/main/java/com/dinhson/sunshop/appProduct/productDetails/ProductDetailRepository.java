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

    @Query("SELECT p FROM ProductDetail p")
    List<ProductDetail> getAll();

    @Query("SELECT SUM(p.number) FROM ProductDetail p WHERE p.product.category.id = :categoryId")
    Integer getNumberProductRemainByCategoryId(Integer categoryId);

    @Query("SELECT SUM(p.number) FROM ProductDetail p WHERE p.color.id = :colorId")
    Integer getNumberProductRemainByColorId(Integer colorId);

    @Query("SELECT p FROM ProductDetail p WHERE p.size.id = :sizeId ORDER BY p.id ASC LIMIT 1")
    Optional<ProductDetail> getFirstBySizeId(Integer sizeId);

    @Query("SELECT p FROM ProductDetail p WHERE p.color.id = :colorId ORDER BY p.id ASC LIMIT 1")
    Optional<ProductDetail> getFirstByColorId(Integer colorId);


}
