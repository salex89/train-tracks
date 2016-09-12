package rs.edu.vtsnis.dmitrovic.train_track.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by danijel on 9/11/16.
 */
@Entity
@NamedQueries(
        {
                @NamedQuery(
                        name = "Station.findAll",
                        query = "SELECT s FROM Station s"
                )
        }
)
public class Station implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty
    private String stationName;

    public Station() {

    }

    public Station(long id, String stationName) {
        this.id = id;
        this.stationName = stationName;
    }

    public long getId() {
        return id;
    }

    public String getStationName() {
        return stationName;
    }
}
