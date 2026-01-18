package org.example.model;

public class RequestAssignment {
    public Long id;
    public Long requestId;     // FK на RepairRequest
    public Long technicianId;  // FK на Technician
    public String role;        // Наприклад: "Lead", "Assistant"
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