package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private int supplier_id;
    @Column(name = "supplier_name")
    private String supplier_name;
    @Column(name = "supplier_adress")
    private String supplier_address;

    @OneToMany(mappedBy = "supplier",cascade = CascadeType.ALL)
    @JsonManagedReference

    private Set<Product> listProduct = new HashSet<>();

    public Supplier(String supplier_name, String supplier_address) {
        this.supplier_name = supplier_name;
        this.supplier_address = supplier_address;
    }
}
