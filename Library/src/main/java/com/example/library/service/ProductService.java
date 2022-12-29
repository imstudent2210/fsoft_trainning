package com.example.library.service;

import com.example.library.dto.ProductDTO;
import com.example.library.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    /*Admin*/
    List<ProductDTO> findAll();
    Product save(MultipartFile imageProduct, ProductDTO productDTO);
    Product update(MultipartFile imageProduct, ProductDTO productDTO);
    void deleteById(Long id);
    void enableById(Long id);
    ProductDTO getById(Long id);

    Page<ProductDTO> pageProducts(int pageNo);

    Page<ProductDTO> searchProducts(int pageNo, String keyword);





    /*Customer*/
    List<Product> getAllProducts();

    List<Product> listViewProducts();

    Product getProductById(Long id);

    List<Product> getRelatedProducts(Long categoryId);

    List<Product> getProductsInCategory(Long categoryId);

    List<Product> filterHighPrice();

    List<Product> filterLowPrice();
}