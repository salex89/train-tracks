package rs.edu.vtsnis.dmitrovic.train_track.models.dao;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import rs.edu.vtsnis.dmitrovic.train_track.models.Station;

import java.util.List;

/**
 * Created by danijel on 9/11/16.
 */
public class StationDAO extends AbstractDAO<Station> implements DAO<Station> {
    public StationDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    @Override
    public List<Station> findAll() {
        return list(namedQuery("Station.findAll"));
    }

    @Override
    public Station findById(long id) {
        return super.get(id);
    }

    @Override
    public long save(Station entity) {
        return super.persist(entity).getId();
    }
}
