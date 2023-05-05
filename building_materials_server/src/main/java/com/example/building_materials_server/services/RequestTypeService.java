package com.example.building_materials_server.services;


import com.example.building_materials_server.models.Material;
import com.example.building_materials_server.models.RequestType;
import com.example.building_materials_server.repositories.RequestTypeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RequestTypeService {
    @Autowired
    private RequestTypeRepository requestTypeRepository;

    public List<RequestType> getAllRequestTypes(){
        return requestTypeRepository.findAll();
    }

}
