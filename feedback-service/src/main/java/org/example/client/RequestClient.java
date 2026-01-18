package org.example.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

// Цей інтерфейс "прикидається" контролером іншого сервісу
@RegisterRestClient(configKey = "request-api")
@Path("/requests")
public interface RequestClient {

    @GET
    @Path("/{id}")
    Response getById(@PathParam("id") Long id);
}