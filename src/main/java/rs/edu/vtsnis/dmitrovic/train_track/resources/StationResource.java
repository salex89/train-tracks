package rs.edu.vtsnis.dmitrovic.train_track.resources;

import io.dropwizard.hibernate.UnitOfWork;
import rs.edu.vtsnis.dmitrovic.train_track.models.IdContainer;
import rs.edu.vtsnis.dmitrovic.train_track.models.Station;
import rs.edu.vtsnis.dmitrovic.train_track.models.StationCollection;
import rs.edu.vtsnis.dmitrovic.train_track.models.dao.StationDAO;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by danijel on 9/11/16.
 */

@Path("/stations")

public class StationResource {
    private StationDAO dao;

    public StationResource(StationDAO dao) {
        this.dao = dao;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public StationCollection getStations() {
        return new StationCollection(dao.findAll());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public IdContainer addStation(@Valid Station station) {
        Long id = dao.save(station);
        return new IdContainer(id);
    }
}
