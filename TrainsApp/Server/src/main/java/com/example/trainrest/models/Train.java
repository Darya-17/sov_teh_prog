package com.example.trainrest.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    @JoinColumn(name="train_type_id")
    private TrainType trainType;
    @OneToMany()
    private List<Carriage> carriages;


    public Train(int id, String name, TrainType trainType, List<Carriage> carriages) {
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



    public List<Carriage> getCarriages() {
        return carriages;
    }

    public void setCarriages(List<Carriage> carriages) {
        this.carriages = carriages;
    }

    @Override
    public String toString() {
        return "Train{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", trainType=" + trainType +


                '}';
    }


}
