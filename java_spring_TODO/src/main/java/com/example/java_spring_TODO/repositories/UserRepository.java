package com.example.java_spring_TODO.repositories;

import com.example.java_spring_TODO.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByfirstName(String firstName);
}
