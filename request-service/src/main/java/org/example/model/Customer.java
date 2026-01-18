package org.example.model;

public class Customer {
    public Long id;
    public String name;
    public String phone;
    public String email;

    public Customer() {}

    public Customer(Long id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
}
