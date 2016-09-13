package rs.edu.vtsnis.dmitrovic.train_track.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;
import rs.edu.vtsnis.dmitrovic.train_track.models.IdContainer;
import rs.edu.vtsnis.dmitrovic.train_track.models.Line;
import rs.edu.vtsnis.dmitrovic.train_track.models.LineCollection;
import rs.edu.vtsnis.dmitrovic.train_track.models.dao.LineDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by danijel on 9/12/16.
 */
@Path("/lines")
public class LineResource {

    private final LineDAO dao;

    public LineResource(LineDAO dao) {
        this.dao = dao;
    }

    @Timed
    @GET
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public LineCollection getLines() {
        return new LineCollection(dao.findAll());
    }

    @Timed
    @POST
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public IdContainer addLine(Line line) {
        long id = dao.save(line);
        return new IdContainer(id);
    }
}
