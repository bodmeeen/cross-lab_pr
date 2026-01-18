package org.example.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class ServiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public Long serviceTypeId;
    public String name;
    public BigDecimal price;
    public Integer durationMinutes;

    public String description;
    public Integer warrantyMonths;

    public ServiceItem() {}

    public ServiceItem(Long typeId, String name, BigDecimal price, Integer duration, String desc) {
        this.serviceTypeId = typeId;
        this.name = name;
        this.price = price;
        this.durationMinutes = duration;
        this.description = desc;
    }
}