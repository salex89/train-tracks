package rs.edu.vtsnis.dmitrovic.train_track.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aleksandar on 9/14/16.
 */
@Entity
@NamedQueries(
        @NamedQuery(name = "Reservations.findByTrainNumber",
                query = "SELECT r FROM Reservations r WHERE r.train.id = :trainId")
)
public class Reservations {

    private final int MAX_SEATS = 60;
    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    private Train train;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Seat> seats;

    public Reservations() {
    }

    public Reservations(Train train) {
        this.train = train;
        this.seats = new ArrayList<>(MAX_SEATS);
        for (int seatNumber = 1; seatNumber <= MAX_SEATS; seatNumber++) {
            seats.add(new Seat(seatNumber));
        }
    }

    public long getId() {
        return id;
    }

    public List<Seat> getSeats() {
        return seats;
    }


    private boolean reserve(int seatNumber, String userid) {
        Seat seat = seats.get(seatNumber);
        if (seat.isFree()) {
            seat.reserve(userid);
            return true;
        }
        return false;
    }
}
