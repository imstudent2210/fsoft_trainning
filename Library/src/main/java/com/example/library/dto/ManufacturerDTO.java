package com.example.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ManufacturerDTO {
    private Long manufacturerId;
    private String manufacturerName;
    private String manufacturerAddress;
    private String manufacturerEmail;
}
