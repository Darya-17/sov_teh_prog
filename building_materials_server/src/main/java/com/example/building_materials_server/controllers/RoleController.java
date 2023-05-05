package com.example.building_materials_server.controllers;

import com.example.building_materials_server.Utils.ResponseType;
import com.example.building_materials_server.Utils.ResponseUtils;
import com.example.building_materials_server.services.RoleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/all")
    public ResponseEntity<String> getAllRoles() throws JsonProcessingException {
        var categories =  roleService.getAllRoles();
        var responseString = ResponseUtils.SerializeObject(categories);
        return new ResponseEntity<>(ResponseUtils.formJsonAnswer(ResponseType.SUCCESS,null, responseString), HttpStatus.OK);
    }
}
