package com.dinhson.sunshop.appProduct.productDetails;

import com.dinhson.sunshop.exception.ProductDetailAlreadyExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductDetailService {

    private final ProductDetailRepository productDetailRepository;

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
        return productDetailRepository.findProductDetailByAll(productDetail.getProduct().getId(),
                        productDetail.getColor().getId(),
                        productDetail.getSize().getId())
                .isPresent();
    }

    public ProductDetail findProductDetailById(int productDetailId){
        return productDetailRepository.getById(productDetailId)
                .orElseThrow(() -> new IllegalArgumentException("Product detail is not found !!!"));
    }

    public int findNumberProductRemainById(int productDetailId){
        return productDetailRepository.findById(productDetailId).get().getNumber();
    }
}
