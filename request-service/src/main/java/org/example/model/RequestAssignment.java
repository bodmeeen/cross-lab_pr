package org.example.model;

public class RequestAssignment {
    public Long id;
    public Long requestId;
    public Long technicianId;
    public String role;
    public String notes;

    public RequestAssignment() {}

    public RequestAssignment(Long id, Long requestId, Long technicianId, String role, String notes) {
        this.id = id;
        this.requestId = requestId;
        this.technicianId = technicianId;
        this.role = role;
        this.notes = notes;
    }
}