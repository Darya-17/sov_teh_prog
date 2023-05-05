package com.example.building_materials_server.repositories;

import com.example.building_materials_server.models.Material;
import com.example.building_materials_server.models.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Integer> {
    Stock findByMaterial(Material material);
}
