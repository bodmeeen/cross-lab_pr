package org.example.frontend.client;

import io.quarkus.oidc.token.propagation.common.AccessToken;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.example.frontend.model.RepairRequest;

import java.util.List;

@RegisterRestClient(configKey = "request-api")
@Path("/requests")
@AccessToken // автоматично передає токен користувача на бекенд
public interface RequestClient {

    @GET
    List<RepairRequest> getAll();
}