package com.example.building_materials_server.controllers;


import com.example.building_materials_server.Utils.ResponseType;
import com.example.building_materials_server.Utils.ResponseUtils;
import com.example.building_materials_server.models.Request;
import com.example.building_materials_server.models.Stock;
import com.example.building_materials_server.services.RequestService;
import com.example.building_materials_server.services.StockService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/requests")
public class RequestController {
    @Autowired
    private RequestService requestService;
    @Autowired
    private StockService stockService;

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody Request request) throws JsonProcessingException {
        requestService.saveRequest(request);
        return new ResponseEntity<>(ResponseUtils.formJsonAnswer(ResponseType.SUCCESS, null, null), HttpStatus.OK);
    }

    @PostMapping("/handle/{id}")
    public ResponseEntity<String> handle(@PathVariable int id) throws JsonProcessingException {
        var request = requestService.getRequestById(id);
        var material = request.getMaterial();
        var stockExist = stockService.getStockByMaterial(material);
        if (stockExist == null) {
            stockExist = new Stock();
            stockExist.setMaterial(request.getMaterial());
        }
        stockExist.setCount(stockExist.getCount() + request.getCount());
        stockService.saveStock(stockExist);
        request.setHandled(true);
        requestService.saveRequest(request);
        return new ResponseEntity<>(ResponseUtils.formJsonAnswer(ResponseType.SUCCESS, null, null), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<String> getAllCategories() throws JsonProcessingException {
        var requests = requestService.getAllRequests();
        var responseString = ResponseUtils.SerializeObject(requests);
        return new ResponseEntity<>(ResponseUtils.formJsonAnswer(ResponseType.SUCCESS, null, responseString), HttpStatus.OK);
    }
}
