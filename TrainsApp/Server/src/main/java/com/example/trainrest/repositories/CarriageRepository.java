package com.example.trainrest.repositories;

import com.example.trainrest.models.Carriage;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarriageRepository extends JpaRepository<Carriage,Long> {

}
