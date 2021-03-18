package com.alcemirjunior.github.resources;


import com.alcemirjunior.github.DTO.StateDTO;
import com.alcemirjunior.github.services.StateService;
import io.quarkus.panache.common.Page;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/states")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StateResource {

    @Inject
    StateService stateService;

    @GET
    public List<StateDTO> findAllPaged (
           @DefaultValue("0") @QueryParam(value = "page") Integer page,
           @DefaultValue("12") @QueryParam(value = "linesPerPage") Integer linesPerPage)
    {
        Page pageRequest = new Page(page,linesPerPage);
        return stateService.findAllPaged(pageRequest);
    }

    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public Long registers(){
        return stateService.registers();

    }

    @POST
    public Response insert (@Valid StateDTO dto){
        stateService.insert(dto);
        return Response.status(Response.Status.CREATED).build();

    }

    @PUT
    @Path("{id}")
    public StateDTO update (@PathParam("id") Long id, @Valid StateDTO dto){
        return stateService.update(id, dto);
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id){
        stateService.delete(id);
        return Response.noContent().build();

    }

}
