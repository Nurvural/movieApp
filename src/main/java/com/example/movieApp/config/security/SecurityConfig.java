package com.example.movieApp.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import com.example.movieApp.dataAccess.abstracts.UserRepository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

/*@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	// private final UserRepository userRepository;

	private final AuthenticationProvider authenticationProvider;
	// private final JwtAuthenticationFilter jwtAuthenticationFilter;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/register").permitAll().antMatchers("/").authenticated()
				.antMatchers("/users").hasRole("ADMIN") 
				.and().formLogin().loginPage("/login").permitAll().and().logout().permitAll();
	}
  /*  @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("{noop}password") 
                .roles("USER");
}*/