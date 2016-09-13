package rs.edu.vtsnis.dmitrovic.train_track.models;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/**
 * Created by danijel on 9/13/16.
 */
public class TimeTable {
    private long lineId;
    @NotEmpty
    private List<Long> times;

    public TimeTable() {
    }

    public TimeTable(long lineId, List<Long> times) {
        this.lineId = lineId;
        this.times = times;
    }


    public long getLine() {
        return lineId;
    }

    public List<Long> getTimes() {
        return times;
    }

    public void setLine(long lineId) {
        this.lineId = lineId;
    }

    public void setTimes(List<Long> times) {
        this.times = times;
    }
}
