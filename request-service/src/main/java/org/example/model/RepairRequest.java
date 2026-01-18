package org.example.model;

import java.math.BigDecimal;

public class RepairRequest {
    public Long id;
    public Long customerId;      // Foreign Key на Customer
    public String deviceModel;
    public String serviceCode;
    public BigDecimal quotedPrice;
    public String status;        // "New", "In Progress", "Completed"
    public String paymentStatus; // "Unpaid", "Paid"

    // Деталі (з таблиці request_detail) можна в Java об'єднати для зручності,
    // але якщо хочеш окремо - тримай окремо. Тут я додав їх сюди для спрощення Fake Repository.
    public String problemDescription;

    public RepairRequest() {}

    public RepairRequest(Long id, Long customerId, String deviceModel, String serviceCode, BigDecimal price, String description) {
        this.id = id;
        this.customerId = customerId;
        this.deviceModel = deviceModel;
        this.serviceCode = serviceCode;
        this.quotedPrice = price;
        this.problemDescription = description;
        this.status = "New";
        this.paymentStatus = "Unpaid";
    }
}