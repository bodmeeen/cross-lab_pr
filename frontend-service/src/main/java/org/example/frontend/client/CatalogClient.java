package org.example.frontend.client;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.example.frontend.model.DeviceModel;
import org.example.frontend.model.ServiceItem;
import java.util.List;

@RegisterRestClient(configKey = "catalog-api")
@Path("/catalog")
public interface CatalogClient {

    @GET
    @Path("/services")
    List<ServiceItem> getAllServices();

    @POST
    @Path("/services")
    @Consumes(MediaType.APPLICATION_JSON)
    void createService(ServiceItem service);

    @GET
    @Path("/devices")
    List<DeviceModel> getAllDevices();

    @POST
    @Path("/devices")
    @Consumes(MediaType.APPLICATION_JSON)
    void createDevice(DeviceModel device);
}