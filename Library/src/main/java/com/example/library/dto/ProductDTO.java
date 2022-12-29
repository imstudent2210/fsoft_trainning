package com.example.library.dto;

import com.example.library.model.Category;
import com.example.library.model.Manufacturer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NegativeOrZero;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private double costPrice;
    private double salePrice;
    private int currentQuantity;
    private float itemWeight;
    private Category category;
    private Manufacturer manufacturer;
    private String image;
    private boolean activated;
    private boolean deleted;
}