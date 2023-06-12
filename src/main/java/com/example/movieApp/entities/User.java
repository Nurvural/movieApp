package com.example.movieApp.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@NotNull
	@NotEmpty
	@Column(name = "firstName")
	@Size(min = 3 , max =20)
	private String firstName;

	@NotNull
	@NotEmpty
	@Size(min = 3 , max =20)
	@Column(name = "lastName")
	private String lastName;

	@Email(message = "Email alanı email formatına uymalıdır")
	@Size(min = 8 , max =30)
	@Column(name = "email")
	private String email;
	
	@NotNull
	@NotEmpty
	@Column(name = "password")
	private String password;
	
	@OneToMany(mappedBy = "user")
    private List<Favorite> favories;
	
	@OneToMany(mappedBy = "user")
    private List<Movie> movies;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles = new ArrayList<>();
}
