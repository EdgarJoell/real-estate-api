package com.example.realestate.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "property")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Sale> saleList;


    public Property() {
    }

    public Property(Long property_id, String address, double price, String size) {
        this.property_id = property_id;
        this.address = address;
        this.price = price;
        this.size = size;
    }


    public Long getProperty_id() {
        return property_id;
    }

    public void setProperty_id(Long property_id) {
        this.property_id = property_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }


    @Override
    public String toString() {
        return "Property{" +
                "property_id=" + property_id +
                ", address='" + address + '\'' +
                ", price=" + price +
                ", size='" + size + '\'' +
                '}';
    }
}
