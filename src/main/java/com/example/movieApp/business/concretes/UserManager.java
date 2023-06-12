package com.example.movieApp.business.concretes;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.movieApp.business.abstracts.UserService;
import com.example.movieApp.business.requests.UserLoginRequest;
import com.example.movieApp.business.requests.UserRegisterRequest;
import com.example.movieApp.business.responses.GetAllUserResponse;
import com.example.movieApp.business.responses.GetByIdUserResponse;
import com.example.movieApp.business.rules.UserBusinessRules;
import com.example.movieApp.core.utilities.results.DataResult;
import com.example.movieApp.core.utilities.results.ErrorResult;
import com.example.movieApp.core.utilities.results.Result;
import com.example.movieApp.core.utilities.results.SuccessDataResult;
import com.example.movieApp.core.utilities.results.SuccessResult;
import com.example.movieApp.dataAccess.abstracts.RoleRepository;
import com.example.movieApp.dataAccess.abstracts.UserRepository;
import com.example.movieApp.entities.User;

@Service
public class UserManager implements UserService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final UserBusinessRules userBusinessRules;
	
	public UserManager(UserRepository userRepository, RoleRepository roleRepository,
			UserBusinessRules userBusinessRules) {

		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.userBusinessRules = userBusinessRules;
	}
	
	@Override
	public Result register(UserRegisterRequest userRegisterRequest) {
		
		User user = new User();
		user.setFirstName(userRegisterRequest.getFirstName());
		user.setLastName(userRegisterRequest.getLastName());
		user.setEmail(userRegisterRequest.getEmail());
		user.setPassword(userRegisterRequest.getPassword());
		//String encodedPassword = passwordEncoder.encode(userRegisterRequest.getPassword());
		//user.setPassword(encodedPassword);
		//user.setRoles(new ArrayList<>(Arrays.asList(roleRepository.findByRoleType("USER")))); 
	
		boolean isEmailExists = this.userBusinessRules.checkIfUserEmailExists(userRegisterRequest.getEmail());
		if (isEmailExists) {
			return new ErrorResult("Bu E-posta adresi mevcut.");
		}
		/*Role userRole = roleRepository.findByRoleType("USER");
		user.setRoles(Collections.singletonList(userRole));
		user.setEnabled(true);*/
		this.userRepository.save(user);
		return new SuccessResult("Kayıt oldunuz");

	}

	@Override
	public Result login(UserLoginRequest userLoginRequest) {

		User user = userRepository.findByEmail(userLoginRequest.getEmail());

		if (user != null && userLoginRequest.getPassword().equals(user.getPassword())) {

			return new SuccessResult("Login işlemi başarılı");
		}

		return new ErrorResult("Login işlemi başarısız");
	}
	@Override
	public DataResult<List<GetByIdUserResponse>> getById(int id) {
	    User user = userRepository.findById(id)
	            .orElseThrow(() -> new NoSuchElementException("Kullanıcı bulunamadı"));

	    GetByIdUserResponse getByIdUserResponse = new GetByIdUserResponse();
	    getByIdUserResponse.setFirstName(user.getFirstName());
	    getByIdUserResponse.setLastName(user.getLastName());
	    getByIdUserResponse.setEmail(user.getEmail());
	    getByIdUserResponse.setPassword(user.getPassword());

	    List<GetByIdUserResponse> getByIdUserResponses = Collections.singletonList(getByIdUserResponse);
	    
	    return new SuccessDataResult<>(getByIdUserResponses, "Kullanıcı getirildi");
	}
	@Override
	public DataResult<List<GetAllUserResponse>> getAll() {
		List<User> users = userRepository.findAll();

		List<GetAllUserResponse> userResponses = users.stream().map(user -> {
			GetAllUserResponse responseItem = new GetAllUserResponse();
			responseItem.setId(user.getId());
			responseItem.setFirstName(user.getFirstName());
			responseItem.setLastName(user.getLastName());
			responseItem.setEmail(user.getEmail());
			responseItem.setPassword(user.getPassword());
			return responseItem;
		}).collect(Collectors.toList());

		return new SuccessDataResult<>(userResponses, "listelendi");
	}

	@Override
	public Result delete(int id) {
		Boolean isIdExists = this.userBusinessRules.idExists(id);

		if (isIdExists == null || isIdExists == false) {
			return new ErrorResult("id bulunamadı");
		}

		this.userRepository.deleteById(id);
		return new SuccessResult("kullanıcı silindi");
	}
	@Override
	public User findUserByEmail(String email) {
		  return userRepository.findByEmail(email);
	}

	

	

	






}
