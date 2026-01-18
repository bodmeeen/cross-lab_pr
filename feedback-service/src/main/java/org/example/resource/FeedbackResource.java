package org.example.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.model.Feedback;
import org.example.repository.FeedbackRepository;
import java.util.List;

@Path("/feedback")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FeedbackResource {

    @Inject
    FeedbackRepository repository;

    @GET
    public List<Feedback> getAll() {
        return repository.listAll();
    }

    @POST
    @Transactional
    public Response create(Feedback feedback) {
        repository.persist(feedback);
        return Response.status(Response.Status.CREATED).entity(feedback).build();
    }
}