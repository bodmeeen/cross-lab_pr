package org.example.frontend.client;

import io.quarkus.oidc.token.propagation.common.AccessToken;
import jakarta.ws.rs.*; // імпорт всього для REST
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.example.frontend.model.RepairRequest;

import java.util.List;

@RegisterRestClient(configKey = "request-api")
@Path("/requests")
@AccessToken // передає токен Alice на бекенд
public interface RequestClient {

    @GET
    List<RepairRequest> getAll();


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void create(RepairRequest request);

    @DELETE
    @Path("/{id}")
    void delete(@PathParam("id") Long id);
}