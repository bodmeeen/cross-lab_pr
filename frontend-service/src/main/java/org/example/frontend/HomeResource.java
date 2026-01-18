package org.example.frontend;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.example.frontend.client.RequestClient;

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
        // отримання даних з бекенду з токеном alice
        var list = requestClient.getAll();

        return requests.data("requests", list);
    }
}