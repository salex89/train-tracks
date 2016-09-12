package rs.edu.vtsnis.dmitrovic.train_track.models.dao;

/**
 * Created by danijel on 9/12/16.
 */
public class RelatedEntityMissingException extends RuntimeException {
    private final long id;
    private final String entity;


    public RelatedEntityMissingException(long id, String entity) {
        this.id = id;
        this.entity = entity;
    }

    public long getId() {
        return id;
    }

    public String getEntity() {
        return entity;
    }
}
