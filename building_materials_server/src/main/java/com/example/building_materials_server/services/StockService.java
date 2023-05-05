package com.example.building_materials_server.services;

import com.example.building_materials_server.models.Material;
import com.example.building_materials_server.models.Stock;
import com.example.building_materials_server.models.User;
import com.example.building_materials_server.repositories.StockRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class StockService {
    @Autowired
    private StockRepository stockRepository;
    public void saveStock(Stock user){
        stockRepository.save(user);
    }

    public List<Stock> getAllStocks(){
        return stockRepository.findAll();
    }
    public Stock getStockByMaterial(Material material){
        return stockRepository.findByMaterial(material);
    }

}
