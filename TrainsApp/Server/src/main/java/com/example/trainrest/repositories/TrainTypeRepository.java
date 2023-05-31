package com.example.trainrest.repositories;

import com.example.trainrest.models.Flight;
import com.example.trainrest.models.TrainType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainTypeRepository extends JpaRepository<TrainType,Long> {
}
