package org.example.frontend;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.example.frontend.client.CatalogClient;
import org.example.frontend.model.DeviceModel;
import org.example.frontend.model.ServiceItem;

import java.math.BigDecimal;
import java.net.URI;

@Path("/ui/catalog")
public class CatalogPageResource {

    @Inject
    Template catalog;

    @Inject
    @RestClient
    CatalogClient catalogClient;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getCatalogPage() {
        var services = catalogClient.getAllServices();
        var devices = catalogClient.getAllDevices();

        // Передаємо обидва списки в HTML
        return catalog.data("services", services)
                .data("devices", devices);
    }

    @POST
    @Path("/add-service")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addService(@FormParam("name") String name,
                               @FormParam("price") BigDecimal price,
                               @FormParam("description") String description,
                               @FormParam("duration") Integer duration) {

        ServiceItem newItem = new ServiceItem();
        newItem.name = name;
        newItem.price = price;
        newItem.description = description;
        newItem.durationMinutes = duration;
        newItem.serviceTypeId = 1L;

        catalogClient.createService(newItem);
        return Response.seeOther(URI.create("/ui/catalog")).build();
    }

    @POST
    @Path("/add-device")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addDevice(@FormParam("brand") String brand,
                              @FormParam("model") String model) {

        DeviceModel newDevice = new DeviceModel(brand, model);

        catalogClient.createDevice(newDevice);
        return Response.seeOther(URI.create("/ui/catalog")).build();
    }
}