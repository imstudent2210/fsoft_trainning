package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.entity.Supplier;
import com.example.demo.error.ProductNotFoundException;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class SupplierController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SupplierRepository supplierRepository;


    @GetMapping("/products")
    public List<Product> allProduct() {
        return productRepository.findAll();
    }

    @PostMapping("/products/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Product newProduct(@RequestBody Product newProduct) {
        return productRepository.save(newProduct);
    }

    @GetMapping("/products/{id}")
    Product findProductById(@PathVariable int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @GetMapping("/suppliers")
    public List<Supplier> allSupplier(){
        return supplierRepository.findAll();
    }
    @PostMapping("/suppliers/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Supplier newSupplier(@RequestBody Supplier newSupplier) {
        return supplierRepository.save(newSupplier);
    }

    @GetMapping("/suppliers/{id}")
    Supplier findSupplierById(@PathVariable int id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @DeleteMapping("/suppliers/{id}")
    void deleteBook(@PathVariable int id) {
        supplierRepository.deleteById(id);
    }
//    @GetMapping("/suppliers")
//    public void allsuppliers(){
//        Product product1 = new Product("MF3RS v2",320000,1);
//        Product product4 = new Product("GTS v2",400000,2);
//        Product product2 = new Product("GTS v3",560000,2);
//        Product product3 = new Product("GAN DOU",1020000,3);
//        Set<Product> Listproduct = new HashSet<>();
//        Listproduct.add(product1);
//        Listproduct.add(product2);
//        Listproduct.add(product3);
//        Listproduct.add(product4);
//
//        Supplier supplier1 = new Supplier("Weilong", "China");
//        Supplier supplier2 = new Supplier("GAN", "USA");
//
//        supplier1.setListProduct(Listproduct);
//        product1.setSupplier(supplier1);
//
//        supplierRepository.save(supplier1);



//    }
}
