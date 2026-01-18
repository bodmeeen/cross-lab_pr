package org.example.model;

public class DeviceModel {
    public Long id;
    public String brand;
    public String model;
    public Boolean isActive;

    public DeviceModel() {}

    public DeviceModel(Long id, String brand, String model) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.isActive = true;
    }
}