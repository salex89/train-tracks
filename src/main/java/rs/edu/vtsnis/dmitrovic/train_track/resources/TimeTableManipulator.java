package rs.edu.vtsnis.dmitrovic.train_track.resources;

import com.google.common.collect.Lists;
import rs.edu.vtsnis.dmitrovic.train_track.models.Line;
import rs.edu.vtsnis.dmitrovic.train_track.models.TimeTable;
import rs.edu.vtsnis.dmitrovic.train_track.models.Train;
import rs.edu.vtsnis.dmitrovic.train_track.models.dao.LineDAO;
import rs.edu.vtsnis.dmitrovic.train_track.models.dao.RelatedEntityMissingException;
import rs.edu.vtsnis.dmitrovic.train_track.models.dao.TrainDAO;

import java.util.List;

/**
 * Created by aleksandar on 9/13/16.
 */
public class TimeTableManipulator {
    private final TrainDAO trainDAO;
    private final LineDAO lineDAO;

    public TimeTableManipulator(TrainDAO trainDAO, LineDAO lineDAO) {
        this.trainDAO = trainDAO;
        this.lineDAO = lineDAO;
    }

    public void saveTrainsFromTimeTable(TimeTable timeTable) {
        Line line = lineDAO.findById(timeTable.getLine());
        if (line == null)
            throw new RelatedEntityMissingException(timeTable.getLine(), "Line");
        for (Long time : timeTable.getTimes()) {
            Train train = new Train(line, time);
            trainDAO.save(train);
        }
    }

    public List<Train> findTrainsForLineNumber(long lineNumber) {
        Line line = lineDAO.findById(lineNumber);
        if (line != null) {
            List<Train> trains = trainDAO.findByLine(line);
            return trains;
        } else {
            return Lists.newArrayList();
        }
    }

    public List<Train> findTrainsForLineNumberAndTime(long lineNumber, long startTime, long endTime) {
        Line line = lineDAO.findById(lineNumber);
        if (line != null) {
            List<Train> trains = trainDAO.findByLineAndTime(line, startTime, endTime);
            return trains;
        } else {
            return Lists.newArrayList();
        }
    }

    public List<Train> findAllTrains() {
        return trainDAO.findAll();
    }

}
