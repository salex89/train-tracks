package rs.edu.vtsnis.dmitrovic.train_track.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;
import rs.edu.vtsnis.dmitrovic.train_track.models.TimeTable;
import rs.edu.vtsnis.dmitrovic.train_track.models.Train;
import rs.edu.vtsnis.dmitrovic.train_track.models.TrainCollection;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by aleksandar on 9/13/16.
 */

@Path("/trains")
public class TrainResource {

    private final TimeTableManipulator timeTableManipulator;

    public TrainResource(TimeTableManipulator timeTableManipulator) {
        this.timeTableManipulator = timeTableManipulator;
    }

    @Timed
    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTrains(@Valid TimeTable trainTimes) {
        timeTableManipulator.saveTrainsFromTimeTable(trainTimes);
        return Response.ok().build();
    }


    @Timed
    @GET
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    @SuppressWarnings("RestParamTypeInspection")
    public TrainCollection getTrains(@Valid @NotNull @QueryParam("line") Long line,
                                     @QueryParam("startTime") Long startTime,
                                     @QueryParam("endTime") Long endTime) {
        List<Train> trains;
        if (startTime != null && endTime != null)
            trains = timeTableManipulator.findTrainsForLineNumberAndTime(line, startTime, endTime);
        else
            trains = timeTableManipulator.findTrainsForLineNumber(line);
        return new TrainCollection(trains);
    }

    @Timed
    @Path("/all")
    @GET
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public TrainCollection getTrainsSimple() {
        List<Train> allTrains = timeTableManipulator.findAllTrains();
        return new TrainCollection(allTrains);
    }


}
