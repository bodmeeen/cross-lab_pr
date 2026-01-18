package org.example.repository;

import jakarta.enterprise.context.ApplicationScoped;
import org.example.model.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class RequestRepository {

    private List<Customer> customers = new ArrayList<>();
    private List<Technician> technicians = new ArrayList<>();
    private List<RepairRequest> requests = new ArrayList<>();
    private List<RequestAssignment> assignments = new ArrayList<>(); // Зв'язок M:N
    private List<Payment> payments = new ArrayList<>();

    public RequestRepository() {
        customers.add(new Customer(1L, "Андрій Шевченко", "050-123-45-67", "sheva@mail.com"));
        customers.add(new Customer(2L, "Олена Пчілка", "097-987-65-43", "olena@mail.com"));

        technicians.add(new Technician(10L, "Богдан Ступка", "Смартфони"));
        technicians.add(new Technician(11L, "Леся Українка", "Ноутбуки"));

        requests.add(new RepairRequest(100L, 1L, "iPhone 11", "SCR_REPL", new BigDecimal("2500.00"), "Розбитий екран"));
        requests.add(new RepairRequest(101L, 2L, "MacBook Air", "KEY_CLEAN", new BigDecimal("1500.00"), "Залипають клавіші"));

        assignments.add(new RequestAssignment(1L, 100L, 10L, "Lead", "Прийняв у роботу"));

        payments.add(new Payment(1L, 100L, new BigDecimal("1000.00"), "Card")); // Часткова оплата
    }


    public List<RepairRequest> getAllRequests() {
        return requests;
    }

    public Optional<RepairRequest> getRequestById(Long id) {
        return requests.stream().filter(r -> r.id.equals(id)).findFirst();
    }

    public List<RepairRequest> getRequestsByCustomerId(Long customerId) {
        return requests.stream()
                .filter(r -> r.customerId.equals(customerId))
                .collect(Collectors.toList());
    }

    public List<Technician> getTechniciansForRequest(Long requestId) {
        List<Long> techIds = assignments.stream()
                .filter(a -> a.requestId.equals(requestId))
                .map(a -> a.technicianId)
                .toList();

        return technicians.stream()
                .filter(t -> techIds.contains(t.id))
                .collect(Collectors.toList());
    }

    public void createRequest(RepairRequest request) {
        long nextId = requests.stream().mapToLong(r -> r.id).max().orElse(0) + 1;
        request.id = nextId;
        requests.add(request);
    }
}