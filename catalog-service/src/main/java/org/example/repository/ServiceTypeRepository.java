package org.example.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.example.model.ServiceType;

@ApplicationScoped
public class ServiceTypeRepository implements PanacheRepository<ServiceType> {
}