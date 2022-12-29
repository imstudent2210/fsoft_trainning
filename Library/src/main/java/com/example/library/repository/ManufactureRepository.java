package com.example.library.repository;

import com.example.library.model.Category;
import com.example.library.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ManufactureRepository extends JpaRepository<Manufacturer, Long> {
    @Query("select c from Manufacturer c where c.isActivated = true and c.isDeleted = false")
    List<Manufacturer> findAllByActivated();

}
