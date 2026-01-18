package org.example.repository;

import jakarta.enterprise.context.ApplicationScoped;
import org.example.model.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class CatalogRepository {

    private List<ServiceType> serviceTypes = new ArrayList<>();
    private List<ServiceItem> services = new ArrayList<>();
    private List<DeviceModel> deviceModels = new ArrayList<>();


    private List<DeviceServiceLink> deviceServiceLinks = new ArrayList<>();


    private record DeviceServiceLink(Long deviceModelId, Long serviceId) {}

    public CatalogRepository() {

        serviceTypes.add(new ServiceType(1L, "Ремонт екранів"));
        serviceTypes.add(new ServiceType(2L, "Заміна акумуляторів"));

        services.add(new ServiceItem(10L, 1L, "Заміна скла (Original)", new BigDecimal("3000.00"), 60, "Оригінальне скло"));
        services.add(new ServiceItem(11L, 1L, "Заміна скла (Copy)", new BigDecimal("1500.00"), 60, "Якісна копія"));
        services.add(new ServiceItem(12L, 2L, "Заміна батареї", new BigDecimal("1200.00"), 40, "Гарантія 6 міс"));

        deviceModels.add(new DeviceModel(50L, "Apple", "iPhone 13"));
        deviceModels.add(new DeviceModel(51L, "Samsung", "Galaxy S22"));

        deviceServiceLinks.add(new DeviceServiceLink(50L, 10L));
        deviceServiceLinks.add(new DeviceServiceLink(50L, 12L));
        deviceServiceLinks.add(new DeviceServiceLink(51L, 12L));
    }

    public List<ServiceItem> getAllServices() {
        return services;
    }

    public List<ServiceItem> getServicesForDeviceModel(Long deviceModelId) {
        List<Long> availableServiceIds = deviceServiceLinks.stream()
                .filter(link -> link.deviceModelId().equals(deviceModelId))
                .map(DeviceServiceLink::serviceId)
                .toList();

        return services.stream()
                .filter(s -> availableServiceIds.contains(s.id))
                .collect(Collectors.toList());
    }

    public Optional<ServiceItem> getServiceById(Long id) {
        return services.stream().filter(s -> s.id.equals(id)).findFirst();
    }
}