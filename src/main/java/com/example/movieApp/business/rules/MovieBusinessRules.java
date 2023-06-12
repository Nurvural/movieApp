package com.example.movieApp.business.rules;

import org.springframework.stereotype.Service;

import com.example.movieApp.dataAccess.abstracts.MovieRepository;

@Service
public class MovieBusinessRules {

	private MovieRepository movieRepository;
	
	public MovieBusinessRules(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}
	
	public boolean idExists(int id) {
		return this.movieRepository.existsById(id);
	
	}
	
}

