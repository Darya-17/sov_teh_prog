package com.example.building_materials_server.repositories;

import com.example.building_materials_server.models.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Integer> {
    List<Material> findByName(String firstName);

}
