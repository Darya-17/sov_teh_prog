package com.example.building_materials_server.repositories;

import com.example.building_materials_server.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface UserRepository extends JpaRepository<User, Integer> {

    User findBylogin(String login);
}
