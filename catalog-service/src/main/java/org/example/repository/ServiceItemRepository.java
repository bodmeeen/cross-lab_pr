package org.example.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.example.model.ServiceItem;

@ApplicationScoped
public class ServiceItemRepository implements PanacheRepository<ServiceItem> {
}