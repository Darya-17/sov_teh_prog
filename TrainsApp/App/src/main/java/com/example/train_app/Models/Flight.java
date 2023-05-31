package com.example.train_app.Models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Objects;

public class Flight {
    private int id;
    private String cityFrom;
    private String cityWhere;
    @JsonFormat(pattern = "dd.MM.yyyy")

    private LocalDate departureDateTime;
    @JsonFormat(pattern = "dd.MM.yyyy")

    private LocalDate arrivalDateTime;


    private int basePrice;
    private int seats;
    private Train train;

    public Flight() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public String getCityWhere() {
        return cityWhere;
    }

    public void setCityWhere(String cityWhere) {
        this.cityWhere = cityWhere;
    }


    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public int getSeats() {
        return seats;
    }

    public LocalDate getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDate departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public LocalDate getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(LocalDate arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }


}
