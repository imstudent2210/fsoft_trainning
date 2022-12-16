package com.example.demo.error;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(int id) {
        super("-----Product id not found : " + id + "-----------");
    }

}
