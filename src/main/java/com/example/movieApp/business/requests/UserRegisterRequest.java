package com.example.movieApp.business.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {
	@NotNull
	@NotEmpty
	@Size(min = 3 , max =20)
	private String firstName;
	@NotNull
	@NotEmpty
	@Size(min = 3 , max =20)
	private String lastName;
	@NotNull
	@NotEmpty
	@Email(message = "Email alanı email formatına uymalıdır")
    private String email;
	@NotNull
	@NotEmpty
	@Size(min = 8 , max =30)
	private String password;
 


}
