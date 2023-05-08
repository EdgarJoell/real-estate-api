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

    public Sale() {
    }

    public Sale(Long sale_id, double price, Date saleDate) {
        this.sale_id = sale_id;
        this.price = price;
        this.saleDate = saleDate;
    }

    public Long getSale_id() {
        return sale_id;
    }

    public void setSale_id(Long sale_id) {
        this.sale_id = sale_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }
}
