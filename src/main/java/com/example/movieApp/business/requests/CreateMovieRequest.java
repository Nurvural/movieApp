package com.example.movieApp.business.requests;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMovieRequest {


	private String movieName;
	private String movieType;
	private boolean trackingStatus;
	private LocalDate releaseDate;
	private int userId;
}

