package com.dinhson.sunshop.appProduct.productDetails;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductDetailRepository extends CrudRepository<ProductDetail, Integer> {

    @Query("SELECT p " +
            "FROM ProductDetail p " +
            "WHERE p.product.id = :productId and p.color.id = :colorId and p.size.id = :sizeId")
    public Optional<ProductDetail> findProductDetailByAll(int productId, int colorId, int sizeId);

    public Optional<ProductDetail> getById(Integer id);
}
