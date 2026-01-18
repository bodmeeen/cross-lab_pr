package org.example.frontend.client;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.example.frontend.model.Feedback;
import java.util.List;

@RegisterRestClient(configKey = "feedback-api")
@Path("/feedback")
public interface FeedbackClient {

    @GET
    List<Feedback> getAll();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void create(Feedback feedback);
}