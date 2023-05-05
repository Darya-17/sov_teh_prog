package com.example.building_materials_server.controllers;

import com.example.building_materials_server.Utils.ResponseType;
import com.example.building_materials_server.Utils.ResponseUtils;
import com.example.building_materials_server.models.User;
import com.example.building_materials_server.services.CategoryService;
import com.example.building_materials_server.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/auth")
    public ResponseEntity<String> auth(@RequestBody User user) throws JsonProcessingException {
        String responseMsg;
        var userCopy = userService.getUserByLogin(user.getLogin());
        if (userCopy != null) {
            if (Objects.equals(userCopy.getPassword(), user.getPassword()))
                responseMsg = ResponseUtils.formJsonAnswer(ResponseType.SUCCESS, null,ResponseUtils.SerializeObject(userCopy));
            else
                responseMsg = ResponseUtils.formJsonAnswer(ResponseType.ERROR, "Неверный пароль!",null);
        } else
            responseMsg = ResponseUtils.formJsonAnswer(ResponseType.ERROR, "Пользователя с таким логином не существует!",null);

        return new ResponseEntity<>(responseMsg, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        try {
            User order = userService.getUserById(id);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        String responseMsg;
        var userCopy = userService.getUserByLogin(user.getLogin());
        if (userCopy != null)
            responseMsg = ResponseUtils.formJsonAnswer(ResponseType.ERROR, "Пользователь с таким логином уже существует!",null);
        else {
            userService.saveUser(user);
            responseMsg = ResponseUtils.formJsonAnswer(ResponseType.SUCCESS, "Успешно!",null);
        }
        return new ResponseEntity<>(responseMsg, HttpStatus.OK);
    }


    @PostMapping("{id}")
    public void delete(@RequestBody Integer id) {
        userService.deleteUser(id);
    }

    @GetMapping(value = "")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

}
