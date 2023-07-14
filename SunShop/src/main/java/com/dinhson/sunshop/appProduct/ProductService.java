package com.dinhson.sunshop.appProduct;

import com.dinhson.sunshop.appAdmin.productManagement.ProductRequestCreate;
import com.dinhson.sunshop.appAdmin.productManagement.ProductRequestUpdate;
import com.dinhson.sunshop.appProduct.categories.Category;
import com.dinhson.sunshop.exception.ProductAlreadyExistException;
import com.dinhson.sunshop.utils.FileUtils;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public void createNewProduct(ProductRequestCreate productRequestCreate, Category category) {
        String fileName = FileUtils.getImageUrl(productRequestCreate.getFile());
        Product product = Product.builder()
                .name(productRequestCreate.getName())
                .price(productRequestCreate.getPrice())
                .description(productRequestCreate.getDescription())
                .isDelete(true)
                .img(fileName)
                .category(category)
                .createDate(LocalDateTime.now())
                .build();
                //new Product(productRequestCreate, category, fileName);
        productRepository.save(product);
    }

    public ProductRequestUpdate findProductRequestUpdateById(Integer productId){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Can not find product!!!"));
        return new ProductRequestUpdate(product);
    }

    public void changeStatus(Integer productId) {
        Product product = findProductById(productId);
        product.setDelete(product.isDelete() ? false : true);
        productRepository.save(product);
    }

    public Product findProductById(Integer productId){
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Can not find product!!!"));
    }

    public void updateProduct(ProductRequestUpdate productRequestUpdate, Category category) {
        Product product = findProductById(productRequestUpdate.getId());
        product.setName(productRequestUpdate.getName());
        product.setPrice(productRequestUpdate.getPrice());
        product.setCategory(category);
        product.setDescription(productRequestUpdate.getDescription());

        if(!productRequestUpdate.getFile().isEmpty()){
            product.setImg(FileUtils.getImageUrl(productRequestUpdate.getFile()));
        }
        productRepository.save(product);
    }

    public Integer getNumberProductByCategoryId(Integer categoryId){
        return productRepository.getNumberProductByCategoryId(categoryId);
    }


}
