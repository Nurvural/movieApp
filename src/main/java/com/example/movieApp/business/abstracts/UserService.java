package com.example.movieApp.business.abstracts;

import java.util.List;

import com.example.movieApp.business.requests.UserLoginRequest;
import com.example.movieApp.business.requests.UserRegisterRequest;
import com.example.movieApp.business.responses.GetAllUserResponse;
import com.example.movieApp.business.responses.GetByIdUserResponse;
import com.example.movieApp.core.utilities.results.DataResult;
import com.example.movieApp.core.utilities.results.Result;
import com.example.movieApp.entities.User;

public interface UserService {

	Result register(UserRegisterRequest userRegisterRequest);
	Result login(UserLoginRequest userLoginRequest);
	Result delete(int id);
	DataResult<List<GetAllUserResponse>> getAll();	
	DataResult<List<GetByIdUserResponse>> getById(int id);
	User findUserByEmail(String email);
	//List<UserRegisterRequest> findAllUsers();

}
