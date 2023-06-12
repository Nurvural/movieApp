package com.example.movieApp.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.movieApp.entities.User;

public interface UserRepository  extends JpaRepository<User, Integer> {

	boolean existsByEmail(String email);
	
	//List<User> findOneByEmailAndPassword(String email, String password);
	//User findByUsername(String username);
	User findByEmail(String email);
}
