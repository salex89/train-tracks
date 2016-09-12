package rs.edu.vtsnis.dmitrovic.train_track.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by danijel on 9/11/16.
 */
public class StationCollection extends JsonSerializableCollection<Station> {

    public StationCollection(List<Station> stations) {
        super(stations);
    }

    @Override
    @JsonProperty("stations")
    public List getItems() {
        return super.getItems();
    }
}
