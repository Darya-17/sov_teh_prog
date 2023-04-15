package com.example.java_spring_TODO.repositories;

import com.example.java_spring_TODO.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findByName(String firstName);
}