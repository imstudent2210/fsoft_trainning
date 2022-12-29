package com.example.library.service;

import com.example.library.dto.CategoryDTO;
import com.example.library.model.Category;

import java.util.List;

public interface CategoryService {
    /*Admin*/
    List<Category> findAll();
    Category save(Category category);
    Category findById(Long id);
    Category update(Category category);
    void deleteById(Long id);
    void enabledById(Long id);
    List<Category> findAllByActivated();

    /*Customer*/
    List<CategoryDTO> getCategoryAndProduct();

    List<Category> findByNameContaining(String categoryName);

}