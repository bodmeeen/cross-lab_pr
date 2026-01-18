package org.example.model;

public class Technician {
    public Long id;
    public String name;
    public String specialization;

    public Technician() {}

    public Technician(Long id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }
}
