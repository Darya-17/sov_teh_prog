package com.example.building_materials_server.services;

import com.example.building_materials_server.models.Category;
import com.example.building_materials_server.models.Material;
import com.example.building_materials_server.repositories.MaterialRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class MaterialService {
    @Autowired
    private MaterialRepository materialRepository;
    public List<Material> getAllMaterials(){
        return materialRepository.findAll();
    }

    public Material getMaterialtById(Integer id){
        return materialRepository.findById(id).get();
    }

}
