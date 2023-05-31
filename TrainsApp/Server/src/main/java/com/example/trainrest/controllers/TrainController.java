package com.example.trainrest.controllers;

import com.example.trainrest.Utils.ResponseType;
import com.example.trainrest.Utils.ResponseUtils;
import com.example.trainrest.models.Carriage;
import com.example.trainrest.models.Flight;
import com.example.trainrest.models.Train;
import com.example.trainrest.services.CarriageService;
import com.example.trainrest.services.FlightService;
import com.example.trainrest.services.TrainService;
import com.example.trainrest.services.TrainTypeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TrainController {
    @Autowired
    TrainService trainService;
    @Autowired
    FlightService flightService;
    @Autowired
    CarriageService carriageService;
    @Autowired
    TrainTypeService trainTypeService;

    @PostMapping("addTrain")
    public ResponseEntity<String> addTrain(@RequestBody Train train){
        for (Carriage carriage : train.getCarriages()) {
            carriageService.addCarr(carriage);
        }
        trainService.addTrain(train);

        return new ResponseEntity<>("Поезд был успешно добавлен", HttpStatus.OK);
    }

    @PostMapping("addFlight")
    public ResponseEntity<?> addFlight(@RequestBody Flight flight){
        flightService.addFlight(flight);
        return new ResponseEntity<>("Полет был успешно добавлен", HttpStatus.OK);
    }
    @GetMapping("/getTrainTypes")
    public ResponseEntity<?> trainTypes() throws JsonProcessingException {
        var trainTypes =  trainTypeService.findAllTrainTypes();
        var responseString = ResponseUtils.SerializeObject(trainTypes);
        return new ResponseEntity<>(ResponseUtils.formJsonAnswer(ResponseType.SUCCESS,null, responseString), HttpStatus.OK);
    }
    @GetMapping("getallTrains")
    public ResponseEntity<?> getallTrains() throws JsonProcessingException {
        var trainTypes =  trainService.findAllTrains();
        var responseString = ResponseUtils.SerializeObject(trainTypes);
        return new ResponseEntity<>(ResponseUtils.formJsonAnswer(ResponseType.SUCCESS,null, responseString), HttpStatus.OK);
    }
    @GetMapping("getallFlights")
    public ResponseEntity<?> getAllFlights() throws JsonProcessingException {
        var flights =  flightService.findAllFlights();
        var responseString = ResponseUtils.SerializeObject(flights);
        return new ResponseEntity<>(ResponseUtils.formJsonAnswer(ResponseType.SUCCESS,null, responseString), HttpStatus.OK);
    }

    @GetMapping("trainsName")
    public ResponseEntity<?> trainsName(){
        List<String> trainNames = trainService.findAllTrains().stream().map(Train::getName).toList();
        return new ResponseEntity<>(trainNames, HttpStatus.OK);
    }
    @GetMapping("allFlights")
    public ResponseEntity<?> allFlights(){
        List<Flight> flights = flightService.findAllFlights();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }


    @PostMapping("/buyTicket")
    public ResponseEntity<?> buyTicket(@RequestParam (name="train") String trainName){
        Train train = trainService.findByName(trainName);
        for (Carriage carriage : train.getCarriages()) {
            if (carriage.getSeats_count() > 0){
                carriage.setSeats_count(carriage.getSeats_count() - 1);
                carriageService.addCarr(carriage);
                return new ResponseEntity<>("You've bought ticket", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("No tickets", HttpStatus.OK);
    }


}
