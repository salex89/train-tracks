package rs.edu.vtsnis.dmitrovic.train_track.resources;

import io.dropwizard.hibernate.UnitOfWork;
import rs.edu.vtsnis.dmitrovic.train_track.models.Reservations;
import rs.edu.vtsnis.dmitrovic.train_track.models.dao.ReservationsDAO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Created by aleksandar on 9/14/16.
 */

@Path("/reservations")
public class ReservationsResource {
    private final ReservationsDAO reservationsDAO;

    public ReservationsResource(ReservationsDAO reservationsDAO) {
        this.reservationsDAO = reservationsDAO;
    }

    @GET
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public Reservations getReservations(@Valid @NotNull @QueryParam("train") Long train) {
        Reservations reservations = reservationsDAO.findByTrainNumber(train);
        return reservations;
    }

}
