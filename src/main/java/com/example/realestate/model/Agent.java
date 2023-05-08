package com.example.realestate.model;

import javax.persistence.*;

@Entity
@Table(name = "agents")
public class Agent {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String phoneNumber;
}
