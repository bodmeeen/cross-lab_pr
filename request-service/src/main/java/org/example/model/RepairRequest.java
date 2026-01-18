package org.example.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class RepairRequest extends PanacheEntity { //дає методи .persist(), .listAll()


    public Long customerId;
    public String deviceModel;
    public String serviceCode;
    public BigDecimal quotedPrice;
    public String status;
    public String paymentStatus;
    public String problemDescription;

    public RepairRequest() {}

    public RepairRequest(Long customerId, String deviceModel, String serviceCode, BigDecimal price, String description) {
        this.customerId = customerId;
        this.deviceModel = deviceModel;
        this.serviceCode = serviceCode;
        this.quotedPrice = price;
        this.problemDescription = description;
        this.status = "New";
        this.paymentStatus = "Unpaid";
    }
}