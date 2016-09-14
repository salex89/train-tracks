package rs.edu.vtsnis.dmitrovic.train_track.models.dao;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import rs.edu.vtsnis.dmitrovic.train_track.models.Reservations;

import java.util.List;

/**
 * Created by aleksandar on 9/14/16.
 */
public class ReservationsDAO extends AbstractDAO<Reservations> implements DAO<Reservations> {
    public ReservationsDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Reservations> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Reservations findById(long id) {
        return get(id);
    }

    @Override
    public long save(Reservations reservations) {

        return super.persist(reservations).getId();
    }

    public Reservations findByTrainNumber(long id) {
        return super.uniqueResult(namedQuery("Reservations.findByTrainNumber").setLong("trainId", id));
    }

}
