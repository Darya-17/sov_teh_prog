package com.example.train_app.Models;

import java.util.List;

public class Train {

    private int id;
    private String name;

    private TrainType trainType;
    private Carriage [] carriages;


    public Train(int id, String name, TrainType trainType,Carriage [] carriages) {
        this.id = id;
        this.name = name;
        this.trainType = trainType;
        this.carriages = carriages;
    }

    public Train() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TrainType getTrainType() {
        return trainType;
    }

    public void setTrainType(TrainType trainType) {
        this.trainType = trainType;
    }



    public Carriage [] getCarriages() {
        return carriages;
    }

    public void setCarriages(Carriage[] carriages) {
        this.carriages = carriages;
    }

    @Override
    public String toString() {
        return name;
    }
}
