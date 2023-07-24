package com.dinhson.sunshop.appProduct.productDetails;

import com.dinhson.sunshop.appAdmin.productManagement.NumberProductResponseDTO;
import com.dinhson.sunshop.appAdmin.productManagement.UpdateNumberProductDTO;
import com.dinhson.sunshop.appCart.CartItem;
import com.dinhson.sunshop.appProduct.Product;
import com.dinhson.sunshop.appProduct.ProductDetailMapProductResponseDTOMapper;
import com.dinhson.sunshop.appProduct.ProductResponseDTO;
import com.dinhson.sunshop.appProduct.ProductService;
import com.dinhson.sunshop.appProduct.colors.Color;
import com.dinhson.sunshop.appProduct.colors.ColorService;
import com.dinhson.sunshop.appProduct.sizes.Size;
import com.dinhson.sunshop.appProduct.sizes.SizeService;
import com.dinhson.sunshop.common.ApiResponse;
import com.dinhson.sunshop.exception.ProductDetailAlreadyExistException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductDetailService {

    private final ProductDetailRepository productDetailRepository;
    private final ProductDetailMapProductResponseDTOMapper productResponseDTOMapper;
    private final ColorService colorService;
    private final SizeService sizeService;
    private final ProductService productService;


    public void addNewProductDetail(ProductDetail productDetail) {
        if (isProductDetailExist(productDetail)) {
            throw new ProductDetailAlreadyExistException("Product " + productDetail.getProduct().getName()
                    + " with color: " + productDetail.getColor().getName()
                    + " and size: " + productDetail.getSize().getSize()
                    + " is already exist!!!");
        }
        productDetailRepository.save(productDetail);
    }

    private boolean isProductDetailExist(ProductDetail productDetail) {
        return productDetailRepository.findProductDetailByAll(
                        productDetail.getProduct().getId(),
                        productDetail.getColor().getId(),
                        productDetail.getSize().getId()
                )
                .isPresent();
    }

    public ProductDetail findProductDetailById(int productDetailId) {
        return productDetailRepository.getById(productDetailId)
                .orElseThrow(() -> new IllegalArgumentException("Product detail is not found !!!"));
    }

    public int findNumberProductRemainById(int productDetailId) {
        return productDetailRepository.findById(productDetailId).get().getNumber();
    }



    public NumberProductResponseDTO getNumberProductRemain(Integer sizeId, Integer colorId, Integer productId) {
        Optional<ProductDetail> productDetail = productDetailRepository.findProductDetailByAll(productId, colorId, sizeId);
        if (productDetail.isPresent()) {
            int numberRemain = productDetail.get().getNumber();
            return new NumberProductResponseDTO("Get success", numberRemain, HttpStatus.OK);
        }
        return new NumberProductResponseDTO("Get success", 0, HttpStatus.BAD_REQUEST);
    }

    public ApiResponse updateProductQuantity(UpdateNumberProductDTO updateNumberProductDTO) {
        Optional<ProductDetail> productDetailOptional = productDetailRepository.findProductDetailByAll(
                updateNumberProductDTO.productId(),
                updateNumberProductDTO.colorId(),
                updateNumberProductDTO.sizeId()
        );

        if (productDetailOptional.isPresent()) {
            ProductDetail productDetail = productDetailOptional.get();
            productDetail.setNumber(productDetail.getNumber() + updateNumberProductDTO.number());
            productDetailRepository.save(productDetail);
            return new ApiResponse("Update product's quantity success!!!", HttpStatus.OK);
        }

        Color color = colorService.findColorById(updateNumberProductDTO.colorId());
        Size size = sizeService.findSizeById(updateNumberProductDTO.sizeId());
        Product product = productService.findProductById(updateNumberProductDTO.productId());
        ProductDetail productDetail = ProductDetail
                .builder()
                .product(product)
                .color(color)
                .size(size)
                .number(updateNumberProductDTO.number())
                .build();
                //new ProductDetail(product, color, size, updateNumberProductDTO.number());
        productDetailRepository.save(productDetail);
        return new ApiResponse("Create and add product's quantity success!!!", HttpStatus.OK);
    }

    public Integer getNumberProductRemainByCategoryId(Integer categoryId) {
        return productDetailRepository.getNumberProductRemainByCategoryId(categoryId);
    }

    public Integer getNumberProductRemainByColorId(Integer colorId) {
        return productDetailRepository.getNumberProductRemainByColorId(colorId);
    }

    public boolean isSizeWasUsed(Integer sizeId) {
        Optional<ProductDetail> productDetail = productDetailRepository.getFirstBySizeId(sizeId);
        return productDetail.isPresent();
    }

    public boolean isColorWasUsed(Integer colorId) {
        Optional<ProductDetail> productDetail = productDetailRepository.getFirstByColorId(colorId);
        return productDetail.isPresent();
    }

    private void updateQuantity(CartItem cartItem) {
        ProductDetail productDetail = cartItem.getProductDetail();
        productDetail.setNumber(productDetail.getNumber() - cartItem.getQuantity());
        productDetailRepository.save(productDetail);
    }

    public void updateQuantityProducts(List<CartItem> cartItems) {
        cartItems.forEach(cartItem -> updateQuantity(cartItem));
    }


}
