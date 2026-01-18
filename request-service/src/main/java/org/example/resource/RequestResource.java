package org.example.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.model.RepairRequest;
import org.example.model.Technician;
import org.example.repository.RequestRepository;
import io.quarkus.grpc.GrpcClient;
import org.example.grpc.CatalogGrpcService;
import org.example.grpc.ServiceIdRequest;
import io.quarkus.security.Authenticated;
import java.util.List;

@Path("/requests")
@Authenticated
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RequestResource {

    @Inject
    RequestRepository repository;

    @GET
    public List<RepairRequest> getAll() {
        return repository.getAllRequests();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        return repository.getRequestById(id)
                .map(request -> Response.ok(request).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Path("/{id}/technicians")
    public List<Technician> getTechnicians(@PathParam("id") Long id) {
        return repository.getTechniciansForRequest(id);
    }

    @POST
    public Response create(RepairRequest request) {
        repository.createRequest(request);
        return Response.status(Response.Status.CREATED).entity(request).build();
    }

    @GrpcClient("catalog-grpc")
    CatalogGrpcService catalogGrpcService;


    @GET
    @Path("/check-service/{id}")
    public String checkService(@PathParam("id") Long id) {
        var response = catalogGrpcService.checkServiceExists(
                ServiceIdRequest.newBuilder().setId(id).build()
        ).await().indefinitely();

        return "Послуга існує? " + response.getExists() + ". Назва: " + response.getName();
    }
}