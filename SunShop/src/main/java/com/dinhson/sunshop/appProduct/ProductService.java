package com.dinhson.sunshop.appProduct;

import com.dinhson.sunshop.appProduct.productDetails.ProductDetail;
import com.dinhson.sunshop.exception.ProductAlreadyExistException;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductDTOMapper productDTOMapper;
    private final ProductResponseDTOMapper productResponseDTOMapper;

    public void addNewProduct(Product product) {
        if (isProductExist(product)) {
            throw new ProductAlreadyExistException("Product " + product.getName() + " already exist!!!");
        }
        productRepository.save(product);
    }

    private boolean isProductExist(Product product) {
        return productRepository.findProductByName(product.getName()).isPresent();
    }

    @Cacheable("products")
    public List<ProductDTO> findAllProductActive() {
        System.out.println("dinh son");
        List<Product> products = productRepository.getAllProductsByIsDelete(false);
        return Optional.ofNullable(products)
                .orElseThrow(() -> new IllegalArgumentException("Can not found any products!!!"))
                .stream()
                .map(productDTOMapper)
                .collect(Collectors.toList());
    }

    public ProductResponseDTO findProductActiveById(Integer productId) {
        return productRepository.findProductActiveById(productId)
                .map(productResponseDTOMapper)
                .orElseThrow(() -> new IllegalArgumentException("Can not found product has id: " + productId));
    }

    private List<Product> searchBy(Boolean isDelete, int categoryId, String searchName){
        if (isDelete != null) {
            if (categoryId != 0 && !searchName.isEmpty()) {
                // search by all
                return productRepository.searchProductByAll(isDelete, categoryId, searchName);
            } else if (categoryId != 0) {
                // search by category and isDelete
                return productRepository.searchProductByCategoryAndIsDelete(isDelete, categoryId);
            } else if (!searchName.isEmpty()) {
                // search by isDelete and search name
                return productRepository.searchProductByIsDeleteAndSearchName(isDelete, searchName);
            } else {
                // search by isDelete
                return productRepository.searchProductByIsDelete(isDelete);
            }
        } else {
            if (categoryId != 0 && !searchName.isEmpty()) {
                // search by color and search name
                return productRepository.searchProductByCategoryAndSearchName(categoryId, searchName);
            } else if (categoryId != 0) {
                // search by color
                return productRepository.searchProductByCategory(categoryId);
            } else if (!searchName.isEmpty()) {
                // search by search name
                return productRepository.searchProductBySearchName(searchName);
            } else {
                // get all
                return productRepository.getAll();
            }
        }
    }

    private Boolean convertStringToBoolean(String status){
        if(status.equals("true")){
            return true;
        } else if (status.equals("false")) {
            return false;
        }
        return null;
    }

    public List<ProductResponseDTO> searchProducts(String status, int categoryId, String searchName){
        return searchBy(convertStringToBoolean(status), categoryId, searchName).stream()
                .map(productResponseDTOMapper)
                .collect(Collectors.toList());
    }

}
