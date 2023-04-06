package com.example.java_spring_posts.services;

import com.example.java_spring_posts.models.Category;
import com.example.java_spring_posts.repositories.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {
    @Autowired
    private CategoryRepository catogoryRepository;

    public List<Category> getAllCategories(){
        return catogoryRepository.findAll();
    }

    public List<Category> getCategoryByName(String name){
        return catogoryRepository.findByName(name);
    }

    public void saveCategory(Category client){
        catogoryRepository.save(client);
    }

    public void deleteCategory(Integer id){
        catogoryRepository.deleteById(id);
    }

    public Category getCategorytById(Integer id){
        return catogoryRepository.findById(id).get();
    }
}
