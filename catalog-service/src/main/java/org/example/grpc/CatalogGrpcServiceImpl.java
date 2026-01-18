package org.example.grpc;

import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import org.example.model.ServiceItem;
import org.example.repository.ServiceItemRepository;

@GrpcService
public class CatalogGrpcServiceImpl implements Catalog {

    @Inject
    ServiceItemRepository serviceRepository;

    @Override
    public Uni<ServiceIdReply> checkServiceExists(ServiceIdRequest request) {

        return Uni.createFrom().item(() -> serviceRepository.findById(request.getId()))
                .map(item -> {
                    if (item != null) {
                        return ServiceIdReply.newBuilder()
                                .setExists(true)
                                .setName(item.name)
                                .setPrice(item.price != null ? item.price.doubleValue() : 0.0)
                                .build();
                    } else {
                        return ServiceIdReply.newBuilder()
                                .setExists(false)
                                .setName("Not Found")
                                .build();
                    }
                });
    }
}