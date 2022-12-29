package com.example.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryDTO {
    private Long categoryId;
    private String categoryName;
    private Long numberOfProduct;
}