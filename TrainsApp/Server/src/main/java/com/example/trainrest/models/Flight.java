package com.example.trainrest.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import serializers.LocalDateAttributeDeserializer;
import serializers.LocalDateAttributeSerializer;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String cityFrom;
    private String cityWhere;
    @JsonDeserialize(converter = LocalDateAttributeDeserializer.class)
    @JsonSerialize(converter = LocalDateAttributeSerializer.class)
    private LocalDate departureDateTime;
    @JsonDeserialize(converter = LocalDateAttributeDeserializer.class)
    @JsonSerialize(converter = LocalDateAttributeSerializer.class)
    private LocalDate arrivalDateTime;
    private int basePrice;
    @OneToOne
    private Train train;


    public LocalDate getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDate departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public Flight(int id, String cityFrom, String cityWhere, int basePrice, Train train) {
        this.id = id;
        this.cityFrom = cityFrom;
        this.cityWhere = cityWhere;
        this.basePrice = basePrice;
        this.train = train;
    }

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

    public LocalDate getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(LocalDate arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }


    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }


}
