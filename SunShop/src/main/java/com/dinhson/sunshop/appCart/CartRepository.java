package com.dinhson.sunshop.appCart;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends CrudRepository<CartItem, Integer> {

    @Query("SELECT c FROM CartItem c WHERE c.productDetail.id = :productDetailId and c.user.id = :userId")
    public Optional<CartItem> findCartItemByProductDetailId(int productDetailId, int userId);

    @Query("SELECT COUNT(c) FROM CartItem c WHERE c.user.id = :userId")
    public int countNumberItemInCart(int userId);

    @Query("SELECT c FROM CartItem c WHERE c.user.id = :userId")
    public List<CartItem> findCartByUserId(int userId);
}
