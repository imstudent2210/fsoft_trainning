package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int product_id;
    @Column(name = "product_name")
    private String product_name;
    @Column(name = "product_price")
    private int product_price;

    // mối quan hệ 1 - n với nhà cung cấp
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = true, referencedColumnName = "supplier_id")
    @JsonBackReference

    private Supplier supplier;

    public Product(String product_name, int product_price, int suppler_id) {
        this.product_name = product_name;
        this.product_price = product_price;
    }

//    public Product(String product_name, int product_price, Supplier supplier) {
//        this.product_name = product_name;
//        this.product_price = product_price;
//        this.supplier = supplier;
//    }
}
