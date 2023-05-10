package com.example.realestate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Long id;

    @Column
    private String address;

    @Column
    private double price;

    @Column
    private int size;

    @OneToMany(mappedBy = "property", orphanRemoval = true)
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Sale> saleList;

    @ManyToOne
    @JoinColumn(name = "agent_id", referencedColumnName = "id")
    private Agent agent;

    public Property() {
    }

    public Property(Long id, String address, double price, int size) {
        this.id = id;
        this.address = address;
        this.price = price;
        this.size = size;
    }

    public List<Sale> getSaleList() {
        return saleList;
    }

    public Agent getAgent() {
        return agent;
    }

    public Long getProperty_id() {
        return id;
    }

    public void setProperty_id(Long property_id) {
        this.id = property_id;
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    @Override
    public String toString() {
        return "Property{" +
                "property_id=" + id +
                ", address='" + address + '\'' +
                ", price=" + price +
                ", size='" + size + '\'' +
                '}';
    }

}
