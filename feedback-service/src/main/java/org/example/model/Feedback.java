package org.example.model;

public class Feedback {
    public Long id;
    public Long requestId;    // Зв'язок з request-service
    public Integer rating;    // 1-5
    public String comment;
    public String customerName; // Ім'я клієнта (щоб не шукати)

    public Feedback() {}

    public Feedback(Long id, Long requestId, String customerName, Integer rating, String comment) {
        this.id = id;
        this.requestId = requestId;
        this.customerName = customerName;
        this.rating = rating;
        this.comment = comment;
    }
}