package com.example.movieApp.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.movieApp.entities.Role;


public interface RoleRepository  extends JpaRepository<Role, Integer>{

	Role findByRoleType(String roleType);
}
