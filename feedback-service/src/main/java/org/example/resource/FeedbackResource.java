package org.example.resource;

import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.example.client.RequestClient;
import org.example.model.Feedback;
import org.example.repository.FeedbackRepository;

import java.util.List;

@Path("/feedback")
@Authenticated
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FeedbackResource {

    @Inject
    FeedbackRepository repository;

    @Inject
    @RestClient
    RequestClient requestClient;

    @GET
    @Path("/request/{requestId}")
    public List<Feedback> getByRequest(@PathParam("requestId") Long requestId) {
        return repository.findByRequestId(requestId);
    }

    @POST
    public Response addFeedback(Feedback feedback) {
        try {
            requestClient.getById(feedback.requestId);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Помилка: Заявка з ID " + feedback.requestId + " не знайдена або сервіс заявок недоступний.")
                    .build();
        }

        repository.addFeedback(feedback);

        return Response.ok(feedback).build();
    }
}