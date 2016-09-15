package rs.edu.vtsnis.dmitrovic.train_track.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by danijel on 9/15/16.
 */
public class ReservationRequest {
    private final int train;
    private final int seat;
    @NotEmpty
    private final String userId;

    @JsonCreator
    public ReservationRequest(@JsonProperty("train") int train,
                              @JsonProperty("seat") int seat,
                              @JsonProperty("userId") String userId) {
        this.train = train;
        this.seat = seat;
        this.userId = userId;
    }

    public int getTrain() {
        return train;
    }

    public String getUserId() {
        return userId;
    }

    public int getSeat() {
        return seat;
    }
}
