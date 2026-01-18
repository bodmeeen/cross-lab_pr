package org.example.model;

import java.math.BigDecimal;

public class Payment {
    public Long id;
    public Long requestId;
    public BigDecimal amount;
    public String paymentMethod; // "Card", "Cash"

    public Payment() {}

    public Payment(Long id, Long requestId, BigDecimal amount, String paymentMethod) {
        this.id = id;
        this.requestId = requestId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }
}