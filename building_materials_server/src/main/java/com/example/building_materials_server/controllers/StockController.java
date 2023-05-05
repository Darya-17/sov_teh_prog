package com.example.building_materials_server.controllers;


import com.example.building_materials_server.Utils.ResponseType;
import com.example.building_materials_server.Utils.ResponseUtils;
import com.example.building_materials_server.services.MaterialService;
import com.example.building_materials_server.services.StockService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stocks")
public class StockController {
    @Autowired
    private StockService stockService;

    @GetMapping("/all")
    public ResponseEntity<String> getAllStocks() throws JsonProcessingException {
        var stocks =  stockService.getAllStocks();
        var responseString = ResponseUtils.SerializeObject(stocks);
        return new ResponseEntity<>(ResponseUtils.formJsonAnswer(ResponseType.SUCCESS,null,responseString), HttpStatus.OK);
    }


}
