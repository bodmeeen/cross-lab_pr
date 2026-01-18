package org.example.frontend;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.example.frontend.client.FeedbackClient;
import org.example.frontend.model.Feedback;

import java.net.URI;

@Path("/ui/feedback")
public class FeedbackPageResource {

    @Inject
    Template feedback;

    @Inject
    @RestClient
    FeedbackClient feedbackClient;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getPage() {
        return feedback.data("feedbacks", feedbackClient.getAll());
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response add(@FormParam("customerName") String customerName,
                        @FormParam("comment") String comment,
                        @FormParam("rating") Integer rating) {

        Feedback fb = new Feedback();
        fb.customerName = customerName;
        fb.comment = comment;
        fb.rating = rating;
        fb.requestId = 1L;

        feedbackClient.create(fb);

        return Response.seeOther(URI.create("/ui/feedback")).build();
    }
}