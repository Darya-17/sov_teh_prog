package com.example.trainrest.repositories;

import com.example.trainrest.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository< Flight,Long> {

}
