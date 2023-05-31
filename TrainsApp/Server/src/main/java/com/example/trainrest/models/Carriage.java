package com.example.trainrest.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Carriage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int seats_count;


    public Carriage(int seats_count) {
        this.seats_count = seats_count;
    }

    public Carriage() {}

    public int getSeats_count() {
        return seats_count;
    }

    public void setSeats_count(int seats_count) {
        this.seats_count = seats_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
