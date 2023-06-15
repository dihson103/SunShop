package com.dinhson.sunshop.appProduct;

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
}
