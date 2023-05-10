package com.example.realestate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;

    public Sale() {
    }

    public Sale(Long sale_id, double price, Date saleDate, Property property) {
        this.sale_id = sale_id;
        this.price = price;
        this.saleDate = saleDate;
        this.property = property;
    }

    public Property getProperty() {
        return property;
    }

    public Agent getAgent() {
        return agent;
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

    public void setProperty(Property property) {
        this.property = property;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "sale_id=" + sale_id +
                ", price=" + price +
                ", saleDate=" + saleDate +
                '}';
    }

}
