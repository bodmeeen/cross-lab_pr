package org.example.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.model.DeviceModel;
import org.example.model.ServiceItem;
import org.example.repository.DeviceModelRepository;
import org.example.repository.ServiceItemRepository;

import java.util.List;

@Path("/catalog")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CatalogResource {

    @Inject
    DeviceModelRepository deviceRepository;

    @Inject
    ServiceItemRepository serviceRepository;


    @GET
    @Path("/devices")
    public List<DeviceModel> getAllDevices() {
        return deviceRepository.listAll();
    }

    @POST
    @Path("/devices")
    @Transactional
    public Response createDevice(DeviceModel device) {
        deviceRepository.persist(device);
        return Response.status(Response.Status.CREATED).entity(device).build();
    }


    @GET
    @Path("/services")
    public List<ServiceItem> getAllServices() {
        return serviceRepository.listAll();
    }

    @POST
    @Path("/services")
    @Transactional
    public Response createService(ServiceItem service) {
        serviceRepository.persist(service);
        return Response.status(Response.Status.CREATED).entity(service).build();
    }
}