package com.example.building_materials_server.services;

import com.example.building_materials_server.models.Request;
import com.example.building_materials_server.repositories.RequestRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RequestService {
    @Autowired
    private RequestRepository requestRepository;
    public List<Request> getAllRequests(){
        return requestRepository.findAll();
    }
    public Request getRequestById(int id){
        return requestRepository.findById(id);
    }
    public void saveRequest(Request request) {
        requestRepository.save(request);
    }

}
