package rs.edu.vtsnis.dmitrovic.train_track.models;

import java.util.List;

/**
 * Created by danijel on 9/12/16.
 */
public class JsonSerializableCollection<T> {
    protected List<T> items;

    public JsonSerializableCollection(List<T> items) {
        this.items = items;
    }

    public void add(T item) {
        this.items.add(item);
    }

    public List<T> getItems() {
        return items;
    }
}
