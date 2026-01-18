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

    // Імітація таблиці зв'язку DeviceServices (M:N)
    // Зберігаємо пару: ID моделі -> ID послуги
    private List<DeviceServiceLink> deviceServiceLinks = new ArrayList<>();

    // Внутрішній клас для зв'язку (аналог рядка в таблиці DeviceServices)
    private record DeviceServiceLink(Long deviceModelId, Long serviceId) {}

    public CatalogRepository() {
        // 1. Типи послуг
        serviceTypes.add(new ServiceType(1L, "Ремонт екранів"));
        serviceTypes.add(new ServiceType(2L, "Заміна акумуляторів"));

        // 2. Послуги
        // Послуга 1 (Екран)
        services.add(new ServiceItem(10L, 1L, "Заміна скла (Original)", new BigDecimal("3000.00"), 60, "Оригінальне скло"));
        // Послуга 2 (Екран копія)
        services.add(new ServiceItem(11L, 1L, "Заміна скла (Copy)", new BigDecimal("1500.00"), 60, "Якісна копія"));
        // Послуга 3 (Батарея)
        services.add(new ServiceItem(12L, 2L, "Заміна батареї", new BigDecimal("1200.00"), 40, "Гарантія 6 міс"));

        // 3. Моделі телефонів
        deviceModels.add(new DeviceModel(50L, "Apple", "iPhone 13"));
        deviceModels.add(new DeviceModel(51L, "Samsung", "Galaxy S22"));

        // 4. Зв'язки (Які послуги підходять до яких телефонів)
        // iPhone 13 (id=50) можна замінити скло Original (id=10)
        deviceServiceLinks.add(new DeviceServiceLink(50L, 10L));
        // iPhone 13 (id=50) можна замінити батарею (id=12)
        deviceServiceLinks.add(new DeviceServiceLink(50L, 12L));

        // Samsung S22 (id=51) підходить тільки заміна батареї (id=12) - умовно
        deviceServiceLinks.add(new DeviceServiceLink(51L, 12L));
    }

    public List<ServiceItem> getAllServices() {
        return services;
    }

    // Знайти послуги, доступні для конкретної моделі телефону
    public List<ServiceItem> getServicesForDeviceModel(Long deviceModelId) {
        // Знаходимо ID послуг, пов'язаних з цією моделлю
        List<Long> availableServiceIds = deviceServiceLinks.stream()
                .filter(link -> link.deviceModelId().equals(deviceModelId))
                .map(DeviceServiceLink::serviceId)
                .toList();

        // Повертаємо самі об'єкти послуг
        return services.stream()
                .filter(s -> availableServiceIds.contains(s.id))
                .collect(Collectors.toList());
    }

    public Optional<ServiceItem> getServiceById(Long id) {
        return services.stream().filter(s -> s.id.equals(id)).findFirst();
    }
}