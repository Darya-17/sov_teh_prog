package com.example.building_materials_server.repositories;

import com.example.building_materials_server.models.RequestType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestTypeRepository extends JpaRepository<RequestType, Integer> {
}
