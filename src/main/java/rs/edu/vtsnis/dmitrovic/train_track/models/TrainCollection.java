package rs.edu.vtsnis.dmitrovic.train_track.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by aleksandar on 9/13/16.
 */
public class TrainCollection extends JsonSerializableCollection {

    public TrainCollection(List items) {
        super(items);
    }

    @Override
    @JsonProperty("trains")
    public List getItems() {
        return super.getItems();
    }
}
