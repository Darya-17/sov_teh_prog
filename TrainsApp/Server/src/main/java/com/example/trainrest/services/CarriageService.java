package com.example.trainrest.services;

import com.example.trainrest.models.Carriage;
import com.example.trainrest.models.Train;
import com.example.trainrest.repositories.CarriageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarriageService {

    @Autowired
    CarriageRepository carriageRepository;

    public void addCarr(Carriage carriage){
        carriageRepository.save(carriage);
    }
}
