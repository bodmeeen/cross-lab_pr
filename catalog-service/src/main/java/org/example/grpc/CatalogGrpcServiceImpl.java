package org.example.grpc;

import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import org.example.repository.CatalogRepository;

@GrpcService
public class CatalogGrpcServiceImpl implements CatalogGrpcService {

    @Inject
    CatalogRepository repository;

    @Override
    public Uni<ServiceExistsResponse> checkServiceExists(ServiceIdRequest request) {
        // Шукаємо послугу в репозиторії
        var serviceOpt = repository.getServiceById(request.getId());

        boolean exists = serviceOpt.isPresent();
        String name = serviceOpt.map(s -> s.name).orElse("Unknown");

        return Uni.createFrom().item(ServiceExistsResponse.newBuilder()
                .setExists(exists)
                .setName(name)
                .build());
    }
}