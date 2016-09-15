package rs.edu.vtsnis.dmitrovic.train_track.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;
import rs.edu.vtsnis.dmitrovic.train_track.models.ReservationRequest;
import rs.edu.vtsnis.dmitrovic.train_track.models.Reservations;
import rs.edu.vtsnis.dmitrovic.train_track.models.dao.ReservationsDAO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by danijel on 9/14/16.
 */

@Path("/reservations")
public class ReservationsResource {
    private final ReservationsDAO reservationsDAO;

    public ReservationsResource(ReservationsDAO reservationsDAO) {
        this.reservationsDAO = reservationsDAO;
    }

    @GET
    @Timed
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public Reservations getReservations(@Valid @NotNull @QueryParam("train") Long train) {
        Reservations reservations = reservationsDAO.findByTrainNumber(train);
        return reservations;
    }

    @POST
    @Timed
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response reserve(@Valid @NotNull ReservationRequest reservationRequest) {
        Reservations reservations = reservationsDAO.findByTrainNumber(reservationRequest.getTrain());
        boolean result = reservations.reserve(reservationRequest.getSeat(), reservationRequest.getUserId());
        if (result)
            return Response.ok().entity(new Message("Reserved!")).build();
        else
            return Response.status(Response.Status.CONFLICT).entity(new Message("Seat already taken")).build();

    }

    private static class Message {
        private final String message;

        public Message(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

}
