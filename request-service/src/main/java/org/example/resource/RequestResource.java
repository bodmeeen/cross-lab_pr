package org.example.resource;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.model.RepairRequest;

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

    @GET
    public List<RepairRequest> getAll() {
        //listAll() сам робить запит до бд; active record
        return RepairRequest.listAll();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        RepairRequest request = RepairRequest.findById(id);
        if (request != null) {
            return Response.ok(request).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


    @POST
    @Transactional
    public Response create(RepairRequest request) {
        if (request.status == null) {
            request.status = "New";
        }
        request.persist();
        return Response.status(Response.Status.CREATED).entity(request).build();
    }


    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, RepairRequest updateData) {
        RepairRequest entity = RepairRequest.findById(id);

        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        entity.deviceModel = updateData.deviceModel;
        entity.problemDescription = updateData.problemDescription;
        entity.status = updateData.status;
        entity.quotedPrice = updateData.quotedPrice;

        return Response.ok(entity).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = RepairRequest.deleteById(id);

        if (deleted) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
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