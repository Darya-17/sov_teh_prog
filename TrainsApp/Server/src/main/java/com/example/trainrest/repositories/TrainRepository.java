package com.example.trainrest.repositories;


import com.example.trainrest.models.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository< Train,Long> {
    Train findByName(String name);
}
