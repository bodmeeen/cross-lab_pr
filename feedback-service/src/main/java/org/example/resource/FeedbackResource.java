package org.example.resource;

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
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FeedbackResource {

    @Inject
    FeedbackRepository repository;

    // Впроваджуємо REST-клієнт для зв'язку з request-service
    @Inject
    @RestClient
    RequestClient requestClient;

    // GET /feedback/request/{requestId} - Всі відгуки до конкретної заявки
    @GET
    @Path("/request/{requestId}")
    public List<Feedback> getByRequest(@PathParam("requestId") Long requestId) {
        return repository.findByRequestId(requestId);
    }

    // POST /feedback - Додати відгук
    @POST
    public Response addFeedback(Feedback feedback) {
        // КРОК 1: Звертаємося до request-service, щоб перевірити, чи існує така заявка
        try {
            // Якщо заявки немає або сервіс недоступний, виникне помилка (або повернеться 404)
            requestClient.getById(feedback.requestId);
        } catch (Exception e) {
            // Якщо сталася помилка — відгук не створюємо
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Помилка: Заявка з ID " + feedback.requestId + " не знайдена або сервіс заявок недоступний.")
                    .build();
        }

        // КРОК 2: Якщо все добре — зберігаємо відгук
        repository.addFeedback(feedback);

        // Повертаємо статус 200 OK і створений об'єкт
        return Response.ok(feedback).build();
    }
}