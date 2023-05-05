package com.example.building_materials_server.repositories;

import com.example.building_materials_server.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Integer> {
    Request findById(int id);
}
