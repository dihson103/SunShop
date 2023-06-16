package com.dinhson.sunshop.appCart;

import com.dinhson.sunshop.appProduct.productDetails.ProductDetail;
import com.dinhson.sunshop.appProduct.productDetails.ProductDetailService;
import com.dinhson.sunshop.appUser.User;
import com.dinhson.sunshop.appUser.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductDetailService productDetailService;
    private final UserService userService;
    private final CartItemDTOMapper cartItemDTOMapper;

    private Optional<CartItem> findItemInCart(int productDetailId, int userId) {
        return cartRepository.findCartItemByProductDetailId(productDetailId, userId);
    }

    private void saveItem(int productDetailId, int userId) {
        User user = userService.findUserById(userId);
        ProductDetail productDetail = productDetailService.findProductDetailById(productDetailId);
        CartItem cartItem = new CartItem(1, user, productDetail);

        cartRepository.save(cartItem);
    }

    public void addItemsToCart(int productDetailId, int userId) {
        int numberProductRemaining = productDetailService.findNumberProductRemainById(productDetailId);
        if (numberProductRemaining > 0) {
            Optional<CartItem> cartItemOptional = findItemInCart(productDetailId, userId);
            if (cartItemOptional.isPresent()) {
                if(cartItemOptional.get().getQuantity() == numberProductRemaining){
                    throw new IllegalArgumentException("Number product remain is not enough!!!");
                }
                changeNumberItemInCart(cartItemOptional.get(), 1);
            } else {
                saveItem(productDetailId, userId);
            }
        } else {
            throw new IllegalArgumentException("This product is sold out!!!");
        }

    }

    private void changeNumberItemInCart(CartItem cartItem, int number) {
        cartItem.setQuantity(number);
        cartRepository.save(cartItem);
    }

    private CartItem findCartItem(int productDetailId, int userId) {
        return findItemInCart(productDetailId, userId)
                .orElseThrow(() -> new IllegalArgumentException("Can not found item!!!"));
    }

    public void deleteItem(int productDetailId, int userId) {
        cartRepository.delete(findCartItem(productDetailId, userId));
    }

    public void changeQuantityItem(int productDetailId, int userId, int number) {
        int numberProductRemaining = productDetailService.findNumberProductRemainById(productDetailId);
        if (number > numberProductRemaining || number < 1) {
            throw new IllegalArgumentException("Number is more than number product remain or less than 1!!!");
        }
        CartItem cartItem = findCartItem(productDetailId, userId);
        changeNumberItemInCart(cartItem, number);
    }

    public int countNumberItemInCart(int userId) {
        return cartRepository.countNumberItemInCart(userId);
    }

    public List<CartItemDTO> findCartByUserId(int userId) {
        return cartRepository.findCartByUserId(userId).stream().map(cartItemDTOMapper).collect(Collectors.toList());
    }


}
