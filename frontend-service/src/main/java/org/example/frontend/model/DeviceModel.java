package org.example.frontend.model;

public class DeviceModel {
    public Long id;
    public String brand;
    public String model;
    public Boolean isActive;

    public DeviceModel() {}

    public DeviceModel(String brand, String model) {
        this.brand = brand;
        this.model = model;
        this.isActive = true;
    }
}