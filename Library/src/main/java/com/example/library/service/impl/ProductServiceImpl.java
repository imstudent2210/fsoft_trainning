package com.example.library.service.impl;
import com.example.library.dto.ProductDTO;
import com.example.library.model.Product;
import com.example.library.repository.ManufactureRepository;
import com.example.library.repository.ProductRepository;
import com.example.library.service.ProductService;
import com.example.library.utils.ImageUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageUpload imageUpload;
    @Override
    public List<ProductDTO> findAll() {
        List<ProductDTO> productDTOList = new ArrayList<>();

        List<Product> productList = productRepository.findAll();
        for(Product product : productList){
            ProductDTO productDTO  = new ProductDTO();
            productDTO.setId(product.getId());
            productDTO.setName(product.getName());
            productDTO.setDescription(product.getDescription());
            productDTO.setCostPrice(product.getCostPrice());
            productDTO.setSalePrice(product.getSalePrice());
            productDTO.setCurrentQuantity(product.getCurrentQuantity());
            productDTO.setItemWeight(product.getItemWeight());
//            productDTO.setImage(productDTO.getImage());
            productDTO.setImage(product.getImage());
            productDTO.setCategory(product.getCategory());
            productDTO.setManufacturer(product.getManufacturer());
//            productDTO.setActivated(productDTO.isActivated());
            productDTO.setActivated(product.isActivated());

            productDTO.setDeleted(product.isDeleted());

            productDTOList.add(productDTO);
        }
        return productDTOList;
    }

    @Override
    public Product save(MultipartFile imageProduct, ProductDTO productDTO) {

       try{
           Product product = new Product();
           if(imageProduct == null){
               product.setImage(null);
           }else{
              if( imageUpload.uploadImage(imageProduct)){
                  System.out.println("Upload successfully");
              }
               product.setImage(Base64.getEncoder().encodeToString(imageProduct.getBytes()));
           }

           product.setName(productDTO.getName());
           product.setDescription(productDTO.getDescription());
           product.setCategory(productDTO.getCategory());
           product.setManufacturer(productDTO.getManufacturer());
           product.setItemWeight(productDTO.getItemWeight());
           product.setCurrentQuantity(productDTO.getCurrentQuantity());
           product.setCostPrice(productDTO.getCostPrice());
           product.setActivated(true);
           product.setDeleted(false);
           return productRepository.save(product);
       }
       catch (Exception e){
           e.printStackTrace();
           return  null;
       }
    }

    @Override
    public Product update(MultipartFile imageProduct ,ProductDTO productDto) {
        try {
            Product product = productRepository.getById(productDto.getId());
            if(imageProduct == null){
                product.setImage(product.getImage());
            }else{
                if(imageUpload.checkExisted(imageProduct) == false){
                    imageUpload.uploadImage(imageProduct);
                }
                product.setImage(Base64.getEncoder().encodeToString(imageProduct.getBytes()));
            }
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setSalePrice(productDto.getSalePrice());
            product.setCostPrice(productDto.getCostPrice());
            product.setItemWeight(productDto.getItemWeight());
            product.setManufacturer(productDto.getManufacturer());
            product.setCurrentQuantity(productDto.getCurrentQuantity());
            product.setCategory(productDto.getCategory());

            return productRepository.save(product);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void deleteById(Long id) {
        Product product = productRepository.getById(id);
        product.setDeleted(true);
        product.setActivated(false);
        productRepository.save(product);
    }

    @Override
    public void enableById(Long id) {
        Product product = productRepository.getById(id);
        product.setDeleted(false);
        product.setActivated(true);
        productRepository.save(product);
    }

    @Override
    public ProductDTO getById(Long id) {
        Product product = productRepository.getById(id);
        ProductDTO productDto = new ProductDTO();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setCurrentQuantity(product.getCurrentQuantity());
        productDto.setCategory(product.getCategory());
        productDto.setManufacturer(product.getManufacturer());
        productDto.setSalePrice(product.getSalePrice());
        productDto.setCostPrice(product.getCostPrice());
        productDto.setItemWeight(product.getItemWeight());
        productDto.setImage(product.getImage());
        productDto.setDeleted(product.isDeleted());
        productDto.setActivated(product.isActivated());
        return productDto;
    }



    private List<ProductDTO> transfer(List<Product> products){
        List<ProductDTO> productDtoList = new ArrayList<>();
        for(Product product : products){
            ProductDTO productDto = new ProductDTO();
            productDto.setId(product.getId());
            productDto.setName(product.getName());
            productDto.setDescription(product.getDescription());
            productDto.setCurrentQuantity(product.getCurrentQuantity());
            productDto.setCategory(product.getCategory());
            productDto.setManufacturer(product.getManufacturer());
            productDto.setSalePrice(product.getSalePrice());
            productDto.setItemWeight(product.getItemWeight());
            productDto.setCostPrice(product.getCostPrice());
            productDto.setImage(product.getImage());
            productDto.setDeleted(product.isDeleted());
            productDto.setActivated(product.isActivated());

            productDtoList.add(productDto);
        }
        return productDtoList;
    }



    @Override
    public Page<ProductDTO> pageProducts(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 4);
        List<ProductDTO> products = transfer(productRepository.findAll());
        Page<ProductDTO> productPages = toPage(products, pageable);
        return productPages;
    }

    @Override
    public Page<ProductDTO> searchProducts(int pageNo, String keyword) {
        Pageable pageable = PageRequest.of(pageNo, 4);
        List<ProductDTO> productDtoList = transfer(productRepository.searchProductsList(keyword));
        Page<ProductDTO> products = toPage(productDtoList, pageable);
        return products;
    }



    private Page toPage(List<ProductDTO> list , Pageable pageable){
        if(pageable.getOffset() >= list.size()){
            return Page.empty();
        }
        int startIndex = (int) pageable.getOffset();
        int endIndex = ((pageable.getOffset() + pageable.getPageSize()) > list.size())
                ? list.size()
                : (int) (pageable.getOffset() + pageable.getPageSize());
        List subList = list.subList(startIndex, endIndex);
        return new PageImpl(subList, pageable, list.size());
    }


    /*Customer*/

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    @Override
    public List<Product> listViewProducts() {
        return productRepository.listViewProducts();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.getById(id);
    }

    @Override
    public List<Product> getRelatedProducts(Long categoryId) {
        return productRepository.getRelatedProducts(categoryId);
    }

    @Override
    public List<Product> getProductsInCategory(Long categoryId) {
        return productRepository.getProductsInCategory(categoryId);
    }

    @Override
    public List<Product> filterHighPrice() {
        return productRepository.filterHighPrice();
    }

    @Override
    public List<Product> filterLowPrice() {
        return productRepository.filterLowPrice();
    }
}
