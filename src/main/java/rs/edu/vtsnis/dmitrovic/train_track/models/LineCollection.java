package rs.edu.vtsnis.dmitrovic.train_track.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by danijel on 9/12/16.
 */
public class LineCollection extends JsonSerializableCollection<Line> {
    public LineCollection(List<Line> items) {
        super(items);
    }

    @Override
    @JsonProperty("lines")
    public List<Line> getItems() {
        return super.getItems();
    }
}
