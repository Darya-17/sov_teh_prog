package com.example.java_spring_posts.services;


import com.example.java_spring_posts.models.User;
import com.example.java_spring_posts.repositories.UserRepository;
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

    public List<User> getClientByfirstName(String firstName){
        return userRepository.findByfirstName(firstName);
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
