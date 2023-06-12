package com.example.movieApp.business.concretes;

import org.springframework.stereotype.Service;

import com.example.movieApp.business.abstracts.ProfileEditService;
import com.example.movieApp.business.requests.UpdateProfileRequest;
import com.example.movieApp.core.utilities.results.Result;
import com.example.movieApp.core.utilities.results.SuccessResult;
import com.example.movieApp.dataAccess.abstracts.UserRepository;
import com.example.movieApp.entities.User;

@Service
public class ProfileEditManager implements ProfileEditService {
	
	private final UserRepository userRepository;
	
	
	public ProfileEditManager(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


	@Override
	public Result update(UpdateProfileRequest updateProfileRequest, int id) throws Exception {

		User user = userRepository.findById(id).orElseThrow(() -> new Exception("Film bulunamadi"));
		user.setFirstName(updateProfileRequest.getFirstName());
		user.setLastName(updateProfileRequest.getLastName());
		user.setPassword(updateProfileRequest.getPassword());
		userRepository.save(user);
		return new SuccessResult("Profil bilgileri güncellendi");
		
	}

}
