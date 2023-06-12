package com.example.movieApp.webApi.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.movieApp.business.abstracts.UserService;
import com.example.movieApp.business.requests.UserLoginRequest;
import com.example.movieApp.business.requests.UserRegisterRequest;
import com.example.movieApp.business.responses.GetAllUserResponse;
import com.example.movieApp.business.responses.GetByIdUserResponse;
import com.example.movieApp.core.utilities.results.DataResult;
import com.example.movieApp.core.utilities.results.Result;

@RestController 
@RequestMapping("/api/users") 
public class UserController {

	private final UserService userService;
	
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping("/register")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Result register(@RequestBody() @Valid UserRegisterRequest userRegisterRequest) {
		 return this.userService.register(userRegisterRequest);
	}

	@PostMapping("/login")
	public Result login(@RequestBody() @Valid UserLoginRequest userLoginRequest) {
		 return this.userService.login(userLoginRequest);
	}
	@GetMapping("/getById/{id}")
	public DataResult<List<GetByIdUserResponse>> getById(@PathVariable int id) {
		return this.userService.getById(id);
	}
	@GetMapping("/getAll")
	public DataResult<List<GetAllUserResponse>> getAll() {
		return this.userService.getAll();
	}
	@DeleteMapping("/{id}")
	public Result delete(@PathVariable int id) {
		return this.userService.delete(id);
	}


}
