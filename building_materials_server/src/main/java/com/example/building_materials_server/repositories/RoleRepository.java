package com.example.building_materials_server.repositories;

import com.example.building_materials_server.models.Material;
import com.example.building_materials_server.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
