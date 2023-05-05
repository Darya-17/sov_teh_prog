package com.example.building_materials_server.services;


import com.example.building_materials_server.models.User;
import com.example.building_materials_server.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserByLogin(String firstName){
        return userRepository.findBylogin(firstName);
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public void deleteUser(Integer id){
        userRepository.deleteById(id);
    }

    public User getUserById(Integer id){
        return userRepository.findById(id).get();
    }
}
