package com.example.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO {
    @Size(min=3, max=10, message = "Invalid first name!")
    private String firstName;
    @Size(min=3, max=10, message = "Invalid last name!")
    private String lastName;
//    @Size(min=3, max=10, message = "Invalid  username!")
    private String userName;
    @Size(min=6, max=12, message = "Invalid password!")
    private String passWord;
    private String repeatPassWord;
}
