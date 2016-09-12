package rs.edu.vtsnis.dmitrovic.train_track.models.dao;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import rs.edu.vtsnis.dmitrovic.train_track.models.Line;
import rs.edu.vtsnis.dmitrovic.train_track.models.Station;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by danijel on 9/12/16.
 */
public class LineDAO extends AbstractDAO<Line> implements DAO<Line> {
    private final StationDAO stationDAO;

    public LineDAO(SessionFactory sessionFactory, StationDAO stationDao) {
        super(sessionFactory);
        this.stationDAO = stationDao;
    }

    @Override
    public List<Line> findAll() {
        return list(namedQuery("Line.findAll"));
    }

    @Override
    public Line findById(long id) {
        return get(id);
    }

    @Override
    public long save(@Valid Line line) {
        validateStationsExist(line.getStations());
        return persist(line).getId();
    }

    private void validateStationsExist(List<Station> stations) {
        for (Station station : stations) {
            Station stationFound = stationDAO.findById(station.getId());
            if (stationFound == null)
                throw new RelatedEntityMissingException(station.getId(), "Station");
        }
    }
}
