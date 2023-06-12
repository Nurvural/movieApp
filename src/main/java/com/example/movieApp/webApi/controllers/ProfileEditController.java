package com.example.movieApp.webApi.controllers;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.movieApp.business.abstracts.ProfileEditService;
import com.example.movieApp.business.requests.UpdateProfileRequest;

@RestController 
@RequestMapping("/api/profile-edit") 
public class ProfileEditController {

	private final ProfileEditService profileEditService;

	public ProfileEditController(ProfileEditService profileEditService) {
		this.profileEditService = profileEditService;
	}
	@PutMapping("/profile/edit")
	public void update(@RequestBody UpdateProfileRequest updateProfileRequest, int id) throws Exception {
		profileEditService.update(updateProfileRequest, id);
	}
}
