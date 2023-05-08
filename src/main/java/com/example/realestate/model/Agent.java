package com.example.realestate.model;

import javax.persistence.*;

@Entity
@Table public class Agent {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
