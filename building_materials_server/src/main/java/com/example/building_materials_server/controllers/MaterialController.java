package com.example.building_materials_server.controllers;


import com.example.building_materials_server.Utils.ResponseType;
import com.example.building_materials_server.Utils.ResponseUtils;
import com.example.building_materials_server.models.Category;
import com.example.building_materials_server.models.Material;
import com.example.building_materials_server.services.MaterialService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/materials")
public class MaterialController {
    @Autowired
    private MaterialService materialService;


    @GetMapping("/all")
    public ResponseEntity<String> getAllMaterials() throws JsonProcessingException {
        var materials =  materialService.getAllMaterials();
        var responseString = ResponseUtils.SerializeObject(materials);
        return new ResponseEntity<>(ResponseUtils.formJsonAnswer(ResponseType.SUCCESS,null,responseString), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Material> getMaterialById(@PathVariable Integer id) {
        try {
            Material material = materialService.getMaterialtById(id);
            return new ResponseEntity<>(material, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
