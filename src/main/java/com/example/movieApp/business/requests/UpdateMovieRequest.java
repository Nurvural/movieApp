package com.example.movieApp.business.requests;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMovieRequest {

	private int id;
	private String movieName;
	private String movieType;
	private boolean trackingStatus;
	private LocalDate releaseDate;
	private int userId;
}
