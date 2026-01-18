package org.example.frontend.model;

public class Feedback {
    public Long id;
    public Long requestId;
    public Integer rating;
    public String comment;
    public String customerName;

    public Feedback() {}

    public Feedback(Long requestId, String customerName, Integer rating, String comment) {
        this.requestId = requestId;
        this.customerName = customerName;
        this.rating = rating;
        this.comment = comment;
    }
}