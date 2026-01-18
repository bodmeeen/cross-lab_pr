package org.example.model;

import jakarta.persistence.*;

@Entity
public class ServiceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;

    public ServiceType() {}

    public ServiceType(String name) {
        this.name = name;
    }
}