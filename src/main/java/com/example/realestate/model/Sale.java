package com.example.realestate.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sales")
public class Sale {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sale_id;

    @Column
    private double price;

    @Column
    private Date saleDate;

}
