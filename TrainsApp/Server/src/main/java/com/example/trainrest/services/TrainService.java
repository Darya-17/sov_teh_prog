package com.example.trainrest.services;

import com.example.trainrest.models.Flight;
import com.example.trainrest.models.Train;
import com.example.trainrest.repositories.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainService {
    @Autowired
    TrainRepository trainRepository;
    public void addTrain(Train t){
        trainRepository.save(t);
    }

    public Train findByName(String name){
        return trainRepository.findByName(name);
    }

    public List<Train> findAllTrains(){
        return trainRepository.findAll();
    }

}
