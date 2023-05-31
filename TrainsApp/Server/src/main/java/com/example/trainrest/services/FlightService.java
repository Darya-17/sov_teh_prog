package com.example.trainrest.services;

import com.example.trainrest.models.Flight;
import com.example.trainrest.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FlightService {
    @Autowired
    FlightRepository flightRepository;

   public void addFlight(Flight f){
       flightRepository.save(f);
   }
   public List<Flight> findAllFlights(){
       return flightRepository.findAll();
   }
}
