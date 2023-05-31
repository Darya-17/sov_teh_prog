package com.example.trainrest.services;

import com.example.trainrest.models.TrainType;
import com.example.trainrest.repositories.TrainTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainTypeService {
    @Autowired
    TrainTypeRepository trainTypeRepository;

    public Optional<TrainType> findById(long id){
        return trainTypeRepository.findById(id);
    }

    public List<TrainType> findAllTrainTypes(){
        return trainTypeRepository.findAll();
    }
}
