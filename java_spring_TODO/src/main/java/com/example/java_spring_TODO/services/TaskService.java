package com.example.java_spring_TODO.services;

import com.example.java_spring_TODO.models.Task;
import com.example.java_spring_TODO.repositories.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public List<Task> getTaskByName(String firstName){
        return taskRepository.findByName(firstName);
    }

    public void saveTask(Task task){
        taskRepository.save(task);
    }

    public void deleteTask(Integer id){
        taskRepository.deleteById(id);
    }

    public Task getTaskById(Integer id){
        return taskRepository.findById(id).get();
    }
}