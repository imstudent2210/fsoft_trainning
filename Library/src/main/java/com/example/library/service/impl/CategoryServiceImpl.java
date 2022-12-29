package com.example.library.service.impl;

import com.example.library.dto.CategoryDTO;
import com.example.library.model.Category;
import com.example.library.repository.CategoryRepository;
import com.example.library.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(Category category) {
        Category categorySave = new Category(category.getName());
//        category1.setDeleted(true);
        return categoryRepository.save(categorySave);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public Category update(Category category) {
        Category categoryUpdate = null;
        try {
            categoryUpdate= categoryRepository.findById(category.getId()).get();
            categoryUpdate.setName(category.getName());
            categoryUpdate.setActivated(category.isActivated());
            categoryUpdate.setDeleted(category.isDeleted());
        }catch (Exception e){
            e.printStackTrace();
        }
        return categoryRepository.save(categoryUpdate);
    }

    @Override
    public void deleteById(Long id) {
        Category category = categoryRepository.getById(id);
        category.setDeleted(true);
        category.setActivated(false);
        categoryRepository.save(category);
    }

    @Override
    public void enabledById(Long id) {
        Category category = categoryRepository.getById(id);
        category.setActivated(true);
        category.setDeleted(false);
        categoryRepository.save(category);
    }

    @Override
    public List<Category> findAllByActivated() {
        return categoryRepository.findAllByActivated();
    }

    @Override
    public List<CategoryDTO> getCategoryAndProduct() {
        return categoryRepository.getCategoryAndProduct();
    }


    @Override
    public List<Category> findByNameContaining(String categoryName) {
        return categoryRepository.findByNameContaining(categoryName);
    }
}
