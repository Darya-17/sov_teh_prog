package com.example.java_spring_TODO.controllers;

import com.example.java_spring_TODO.models.Category;
import com.example.java_spring_TODO.models.Task;
import com.example.java_spring_TODO.models.User;
import com.example.java_spring_TODO.services.CategoryService;
import com.example.java_spring_TODO.services.TaskService;
import com.example.java_spring_TODO.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        try {
            User order = userService.getUserById(id);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<String> add(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>("Пользователь был успешно добавлен", HttpStatus.OK);
    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<Set<Task>> gettasks(@PathVariable Integer id) {
        try {
            User user = userService.getUserById(id);
            var tasks = taskService.getAllTasks().stream().filter(it -> it.getUser().getId() == id).collect(Collectors.toSet());
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("{id}")
    public void delete(@RequestBody Integer id) {
        userService.deleteUser(id);
    }

    @GetMapping(value = "")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/{id}/tasks/{category_id}/")
    public ResponseEntity<String> addTask(@PathVariable Integer id, @PathVariable Integer category_id, @RequestBody Task task) {
        User user = userService.getUserById(id);
        task.setUser(user);
        Category category = categoryService.getCategorytById(category_id);
        task.setCategory(category);
        taskService.saveTask(task);
        return new ResponseEntity<>("Задание было успешно добавлено", HttpStatus.OK);
    }

    @PostMapping("/{id}/tasks/{task_id}/update")
    public ResponseEntity<String> updateTask(@PathVariable Integer id, @PathVariable Integer task_id, @RequestBody Task task) {
        try {
            Task existingTask = taskService.getTaskById(task_id);
            existingTask.updateTask(task);
            taskService.saveTask(existingTask);
            return new ResponseEntity<>("Задание было успешно сохранено", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Такого Задания не существует!", HttpStatus.NOT_FOUND);
        }


    }

    @DeleteMapping("/{id}/tasks/")
    public ResponseEntity<String> deleteTask(@PathVariable Integer id) {

        try {
            taskService.deleteTask(id);
            return new ResponseEntity<>("Задание было успешно удалено", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Такого Задания не существует!", HttpStatus.NOT_FOUND);
        }
    }
}
