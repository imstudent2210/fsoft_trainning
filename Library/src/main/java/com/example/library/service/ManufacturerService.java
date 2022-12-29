package com.example.library.service;

import com.example.library.dto.CategoryDTO;
import com.example.library.model.Category;
import com.example.library.model.Manufacturer;

import java.util.List;

public interface ManufacturerService {

    /*Admin*/
    List<Manufacturer> findAll();
    Manufacturer save(Manufacturer manufacturer);
    Manufacturer findById(Long id);

    Manufacturer update(Manufacturer manufacturer);

    void deleteById(Long id);


    void enabledById(Long id);

    List<Manufacturer> findAllByActivated();


}