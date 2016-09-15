package rs.edu.vtsnis.dmitrovic.train_track.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by danijel on 9/14/16.
 */
@Entity
public class Seat {
    @Id
    @GeneratedValue
    @JsonIgnore
    private long id;
    private int number;
    private boolean free;
    private String userid;


    public Seat() {
    }

    public Seat(int number) {
        this.number = number;
        free = true;
        userid = null;
    }

    public void reserve(String userid) {
        this.free = false;
        this.userid = userid;
    }

    public int getNumber() {
        return number;
    }

    public boolean isFree() {
        return free;
    }

    @JsonIgnore
    public String getUserid() {
        return userid;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
