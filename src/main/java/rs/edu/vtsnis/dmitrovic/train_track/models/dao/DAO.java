package rs.edu.vtsnis.dmitrovic.train_track.models.dao;

import java.util.List;

/**
 * Created by danijel on 9/12/16.
 */
public interface DAO<T> {
    List<T> findAll();

    T findById(long id);

    long save(T line);
}
