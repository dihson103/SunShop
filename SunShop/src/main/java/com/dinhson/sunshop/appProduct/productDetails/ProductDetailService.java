package com.dinhson.sunshop.appProduct.productDetails;

import com.dinhson.sunshop.appProduct.ProductDetailMapProductResponseDTOMapper;
import com.dinhson.sunshop.appProduct.ProductResponseDTO;
import com.dinhson.sunshop.appUser.Role;
import com.dinhson.sunshop.appUser.User;
import com.dinhson.sunshop.exception.ProductDetailAlreadyExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class ProductDetailService {

    private final ProductDetailRepository productDetailRepository;
    private final ProductDetailMapProductResponseDTOMapper productResponseDTOMapper;


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

    public ProductDetail findProductDetailById(int productDetailId) {
        return productDetailRepository.getById(productDetailId)
                .orElseThrow(() -> new IllegalArgumentException("Product detail is not found !!!"));
    }

    public int findNumberProductRemainById(int productDetailId) {
        return productDetailRepository.findById(productDetailId).get().getNumber();
    }

    private List<ProductDetail> searchBy(Boolean isDelete, int colorId, String searchName){
        if (isDelete != null) {
            if (colorId != 0 && !searchName.isEmpty()) {
                // search by all
                return productDetailRepository.searchProductDetailByAll(isDelete, colorId, searchName);
            } else if (colorId != 0) {
                // search by color and isDelete
                return productDetailRepository.searchProductDetailByColorAndIsDelete(colorId, isDelete);
            } else if (!searchName.isEmpty()) {
                // search by isDelete and search name
                return productDetailRepository.searchProductDetailByIsDeleteAndSearchName(isDelete, searchName);
            } else {
                // search by isDelete
                return productDetailRepository.searchProductDetailByIsDelete(isDelete);
            }
        } else {
            if (colorId != 0 && !searchName.isEmpty()) {
                // search by color and search name
                return productDetailRepository.searchProductDetailByColorAndSearchName(colorId, searchName);
            } else if (colorId != 0) {
                // search by color
                return productDetailRepository.searchProductDetailByColor(colorId);
            } else if (!searchName.isEmpty()) {
                // search by search name
                return productDetailRepository.searchProductDetailBySearchName(searchName);
            } else {
                // get all
                return productDetailRepository.getAll();
            }
        }
    }

    public List<ProductResponseDTO> searchProducts(String status, int colorId, String searchName){
        Boolean isDelete;
        if(status.equals("true")){
            isDelete = true;
        } else if (status.equals("false")) {
            isDelete = false;
        }else {
            isDelete = null;
        }
        return searchBy(isDelete, colorId, searchName).stream()
                .map(productResponseDTOMapper)
                .collect(Collectors.toList());
    }



}
