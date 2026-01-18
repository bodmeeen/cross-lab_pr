package org.example.model;

import java.math.BigDecimal;

public class ServiceItem {
    public Long id;
    public Long serviceTypeId;
    public String name;
    public BigDecimal price;
    public Integer durationMinutes;

    public String description;
    public Integer warrantyMonths;

    public ServiceItem() {}

    public ServiceItem(Long id, Long typeId, String name, BigDecimal price, Integer duration, String desc) {
        this.id = id;
        this.serviceTypeId = typeId;
        this.name = name;
        this.price = price;
        this.durationMinutes = duration;
        this.description = desc;
    }
}