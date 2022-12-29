package com.example.library.repository;

import com.example.library.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    /*Admin*/
    @Query("select p from Product p")
    Page<Product> pageProduct(Pageable pageable);

//    @Query("select p from Product p where p.description like %?1% or p.name like %?1%" )
//    Page<Product> searchProducts(String keyword, Pageable pageable);

//    @Query("select p from Product p where p.description like %?1% or p.name like %?1%")
//    List<Product> searchProductsList(String keyword);

    @Query("select p from Product p join Category c  on c.id = p.category join Manufacturer m on p.manufacturer =m.id where  p.name like %?1% or c.name like %?1% or m.name like %?1%" )
    List<Product> searchProductsList(String keyword);

//    @Query("select p  from Product p join Category c on c.id = p.category where p.description like %?1% or p.name like %?1% or c.name like %?1%" )
//    Page<Product> searchProducts(String keyword, Pageable pageable);


    /*Customer*/
    @Query("select p from Product p where p.isActivated = true and p.isDeleted = false")
    List<Product> getAllProducts();


    @Query(value = "select * from Product p where p.isDeleted = false and p.isActivated = true order by rand() asc limit 4 ", nativeQuery = true)
    List<Product> listViewProducts();


    @Query(value = "select * from Product p inner join Category c on c.category_id = p.category_id where p.category_id = ?1", nativeQuery = true)
    List<Product> getRelatedProducts(Long categoryId);


    @Query(value = "select p from Product p inner join Category c on c.id = p.category.id where c.id = ?1 and p.isDeleted = false and p.isActivated = true")
    List<Product> getProductsInCategory(Long categoryId);


    @Query("select p from Product p where p.isActivated = true and p.isDeleted = false" +
            " order by p.costPrice desc")
    List<Product> filterHighPrice();


    @Query("select p from Product p where p.isActivated = true and p.isDeleted = false order by p.costPrice ")
    List<Product> filterLowPrice();
}
