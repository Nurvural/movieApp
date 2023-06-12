package com.example.movieApp.business.rules;

import org.springframework.stereotype.Service;

import com.example.movieApp.dataAccess.abstracts.RoleRepository;
import com.example.movieApp.dataAccess.abstracts.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserBusinessRules {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	
	public boolean checkIfUserEmailExists(String email) {
		if(this.userRepository.existsByEmail(email)) {
			return true;
		}
	
		return false;
	}
	public boolean idExists(int id) {
		return this.userRepository.existsById(id);
	
	}
}
