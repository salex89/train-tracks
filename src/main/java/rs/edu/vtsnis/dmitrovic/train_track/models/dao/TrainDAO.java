package rs.edu.vtsnis.dmitrovic.train_track.models.dao;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import rs.edu.vtsnis.dmitrovic.train_track.models.Line;
import rs.edu.vtsnis.dmitrovic.train_track.models.Train;

import java.util.List;

/**
 * Created by aleksandar on 9/13/16.
 */
public class TrainDAO extends AbstractDAO<Train> implements DAO<Train> {
    private final LineDAO lineDAO;

    public TrainDAO(SessionFactory sessionFactory, LineDAO lineDAO) {
        super(sessionFactory);
        this.lineDAO = lineDAO;
    }

    @Override
    public List<Train> findAll() {
        return list(namedQuery("Train.findAll"));
    }

    @Override
    public Train findById(long id) {
        return get(id);
    }

    @Override
    public long save(Train train) {
        return persist(train).getId();
    }

    public void saveTrainsUnchecked(List<Train> trains) {
        trains.forEach(this::persist);
    }

    public List<Train> findByLine(Line l) {
        return list(namedQuery("Train.findByLine").setParameter("line", l));
    }

    public List<Train> findByLineAndTime(Line l, long startTime, long endTime) {
        return list(namedQuery("Train.findByLineAndTime")
                .setParameter("line", l)
                .setParameter("startTime", startTime)
                .setParameter("endTime", endTime)
        );
    }

}
