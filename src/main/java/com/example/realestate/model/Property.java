package com.example.realestate.model;

import javax.persistence.*;

@Entity
@Table(name = "properties")
public class Property {

    @Id
    @Column
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long property_id;

    @Column
    private String address;

    @Column
    private double price;

    @Column
    private String size;

}
