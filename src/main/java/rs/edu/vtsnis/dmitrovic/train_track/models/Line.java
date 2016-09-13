package rs.edu.vtsnis.dmitrovic.train_track.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

/**
 * Created by danijel on 9/12/16.
 */
@Entity
@NamedQueries(
        {
                @NamedQuery(
                        name = "Line.findAll",
                        query = "SELECT l FROM Line l"
                )
        }
)
public class Line {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    private String name;

    @ManyToMany
    private List<Station> stations;

    public Line() {
    }

    public Line(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }
}
