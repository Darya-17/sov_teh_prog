package com.example.building_materials_server.repositories;

import com.example.building_materials_server.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findByName(String firstName);
}