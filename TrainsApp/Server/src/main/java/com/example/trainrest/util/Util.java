package com.example.trainrest.util;

import com.example.trainrest.models.Carriage;
import com.example.trainrest.models.Train;
import com.example.trainrest.services.CarriageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public static List<Carriage> addCarriages(Train train, int amountCarr, int seats) {
        //создаем вагоны и добавляем их в поезд. В каждом поезде по amountCarr вагонов
        List<Carriage> carriages = new ArrayList<>();
        for (int i = 0; i < amountCarr; i++) {
            List<Boolean> b = new ArrayList<>();
            Carriage carriage = new Carriage(seats);
            carriages.add(carriage);
        }
        return carriages;


    }


}
