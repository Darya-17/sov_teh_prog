package com.example.train_app.Models;

import java.util.List;

public class Carriage {
    private int id;
    int seats_count;

    public int getSeats_count() {
        return seats_count;
    }

    public void setSeats_count(int seats_count) {
        this.seats_count = seats_count;
    }

    public Carriage(int freeSeats) {
        this.seats_count = freeSeats;
    }

    public Carriage(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
