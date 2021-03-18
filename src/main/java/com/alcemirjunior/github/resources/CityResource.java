package com.alcemirjunior.github.resources;


import com.alcemirjunior.github.DTO.CityDTO;
import com.alcemirjunior.github.services.CityService;
import io.quarkus.panache.common.Page;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/cities")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CityResource {

    @Inject
    CityService cityService;

    @GET
    public List<CityDTO> findAllPaged(
            @DefaultValue("0") @QueryParam(value = "page") Integer page,
            @DefaultValue("12") @QueryParam(value = "linesPerPage") Integer linesPerPage)
    {
        Page pageRequest = new Page(page,linesPerPage);
        return cityService.findAllPaged(pageRequest);
    }

    @GET
    @Path("{stateId}")
    public List<CityDTO> findByState(
            @PathParam("stateId") Long stateId,
            @DefaultValue("0") @QueryParam(value = "page") Integer page,
            @DefaultValue("12") @QueryParam(value = "linesPerPage") Integer linesPerPage)
    {
        Page pageRequest = new Page(page,linesPerPage);
        return cityService.findByState(stateId, pageRequest);
    }

    @GET
    @Path("/nameSearch/{cityName}")
    public List<CityDTO> findByCityName(
            @PathParam("cityName") String cityName,
            @DefaultValue("0") @QueryParam(value = "page") Integer page,
            @DefaultValue("12") @QueryParam(value = "linesPerPage") Integer linesPerPage)
    {
        Page pageRequest = new Page(page,linesPerPage);
        return cityService.findByCityName(cityName, pageRequest);

    }

    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public Long registers(){
        return cityService.registers();
    }

    @POST
    public Response insert (@Valid CityDTO dto){
        cityService.insert(dto);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    public CityDTO update (@PathParam("id") Long id, @Valid CityDTO dto){
        return cityService.update(id, dto);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete (@PathParam("id") Long id){
        cityService.delete(id);
        return Response.noContent().build();
    }

}
