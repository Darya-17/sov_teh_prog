package com.example.java_spring_posts.repositories;


import com.example.java_spring_posts.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findByName(String firstName);
}