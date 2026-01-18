package org.example.frontend;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.*; // GET, POST, FormParam, PathParam
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.example.frontend.client.RequestClient;
import org.example.frontend.model.RepairRequest;

import java.math.BigDecimal;
import java.net.URI;

@Path("/")
public class HomeResource {

    @Inject
    Template home;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getHome() {
        return home.instance();
    }

    @Inject
    Template requests;

    @Inject
    @RestClient
    RequestClient requestClient;

    @GET
    @Path("/ui/requests")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getRequests() {
        var list = requestClient.getAll();
        return requests.data("requests", list);
    }

    //обробка кнопки створити, post форми
    @POST
    @Path("/ui/requests/add")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addRequest(@FormParam("deviceModel") String deviceModel,
                               @FormParam("problemDescription") String problemDescription,
                               @FormParam("quotedPrice") BigDecimal quotedPrice) {

        // створення об'єкта із даних форми
        RepairRequest newRequest = new RepairRequest();
        newRequest.deviceModel = deviceModel;
        newRequest.problemDescription = problemDescription;
        newRequest.quotedPrice = quotedPrice;
        newRequest.status = "New";

        requestClient.create(newRequest);

        return Response.seeOther(URI.create("/ui/requests")).build();
    }

    //оброка видалення , post
    @POST
    @Path("/ui/requests/delete/{id}")
    public Response deleteRequest(@PathParam("id") Long id) {

        requestClient.delete(id);

        return Response.seeOther(URI.create("/ui/requests")).build();
    }
}