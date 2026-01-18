package org.example.resource;

import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.model.ServiceItem;
import org.example.repository.CatalogRepository;

import java.util.List;

@Path("/catalog")
@Authenticated
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CatalogResource {

    @Inject
    CatalogRepository repository;

    @GET
    @Path("/services")
    public List<ServiceItem> getAllServices() {
        return repository.getAllServices();
    }

    @GET
    @Path("/models/{modelId}/services")
    public List<ServiceItem> getServicesForModel(@PathParam("modelId") Long modelId) {
        return repository.getServicesForDeviceModel(modelId);
    }
}