package org.example.frontend.model;

import java.math.BigDecimal;

public class ServiceItem {
    public Long id;
    public Long serviceTypeId;
    public String name;
    public BigDecimal price;
    public Integer durationMinutes;
    public String description;

    public ServiceItem() {}

    public ServiceItem(String name, BigDecimal price, String description, Integer durationMinutes) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.durationMinutes = durationMinutes;
    }
}