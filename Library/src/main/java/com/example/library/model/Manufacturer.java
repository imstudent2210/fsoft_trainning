package com.example.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "manufacturer", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manufacturer_id")
    private Long id;
    private String name;
    private String address;
    private String email;

    private boolean isDeleted;
    private boolean isActivated;

    public Manufacturer(String name, String address, String email){
        this.name = name;
        this.address = address;
        this.email = email;
        this.isActivated = true;
        this.isDeleted = false;
    }
}
