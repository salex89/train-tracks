package rs.edu.vtsnis.dmitrovic.train_track.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by aleksandar on 9/13/16.
 */
@Entity
@NamedQueries(
        {
                @NamedQuery(
                        name = "Train.findAll",
                        query = "SELECT t FROM Train t"
                ),
                @NamedQuery(
                        name = "Train.findByLine",
                        query = "SELECT t from Train t WHERE t.line = :line"
                ),
                @NamedQuery(
                        name = "Train.findByLineAndTime",
                        query = "SELECT t from Train t WHERE t.line = :line AND t.time >= :startTime AND t.time <= :endTime"
                )
        }
)
public class Train {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @ManyToOne
    private Line line;

    @NotNull
    private Long time;

    public Train() {
    }

    public Train(Line line, Long time) {
        this.line = line;
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public Line getLine() {
        return line;
    }

    public Long getTime() {
        return time;
    }
}
