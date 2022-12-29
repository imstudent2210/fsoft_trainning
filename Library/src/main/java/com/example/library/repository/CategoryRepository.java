package com.example.library.repository;

import com.example.library.dto.CategoryDTO;
import com.example.library.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    @Query("select c from Category c where c.isActivated = true and c.isDeleted = false")
    List<Category> findAllByActivated();


    /*Customer*/
    @Query("select new com.example.library.dto.CategoryDTO(c.id, c.name, count(p.category.id)) from Category c inner join Product p on p.category.id = c.id " +
            " where c.isActivated = true and c.isDeleted = false group by c.id")
    List<CategoryDTO> getCategoryAndProduct();

    List<Category> findByNameContaining(String categoryName);
}
