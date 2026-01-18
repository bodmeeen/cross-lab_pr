package org.example.frontend.model;

import java.math.BigDecimal;

public class RepairRequest {
    public Long id;
    public String deviceModel;
    public String status;
    public BigDecimal quotedPrice;
    public String problemDescription;

    public RepairRequest() {}
}