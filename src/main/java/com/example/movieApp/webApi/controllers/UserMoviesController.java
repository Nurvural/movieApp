package com.example.movieApp.webApi.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.movieApp.business.abstracts.UserMovieService;
import com.example.movieApp.business.requests.CreateMovieRequest;
import com.example.movieApp.business.requests.UpdateMovieRequest;
import com.example.movieApp.business.responses.GetAllMoviesResponse;
import com.example.movieApp.core.utilities.results.DataResult;
import com.example.movieApp.core.utilities.results.Result;

@RestController
@RequestMapping("/api/movies")
public class UserMoviesController {

	private final UserMovieService movieService;

	public UserMoviesController(UserMovieService movieService) {

		this.movieService = movieService;

	}
	@GetMapping("/getall")
	public DataResult<List<GetAllMoviesResponse>> getAll() {
		return this.movieService.getAll();
	}

	@PostMapping()
	@ResponseStatus(code = HttpStatus.CREATED)
	public Result add(@RequestBody() @Valid CreateMovieRequest createMovieRequest) {
		return this.movieService.add(createMovieRequest);
	}

	@PutMapping("/update/")
	public void update(@RequestBody UpdateMovieRequest updateMovieRequest, int id) throws Exception {
		movieService.update(updateMovieRequest, id);
	}

	@DeleteMapping("/{id}")
	public Result delete(@PathVariable int id) {
		return this.movieService.delete(id);
	}

	@GetMapping("/getsearch/Movie")
	public DataResult<List<GetAllMoviesResponse>> search(@RequestParam String keyword) {
		return movieService.search(keyword);
	}

	@GetMapping("/getfilter/")
	public DataResult<List<GetAllMoviesResponse>> trackingStatusFilter(@RequestParam boolean trackingStatus) {
		return movieService.trackingStatusFilter(trackingStatus);
	}

	@GetMapping("/sortedByReleaseDate")
	public DataResult<List<GetAllMoviesResponse>> getMoviesSortedByReleaseDate() {
		return movieService.getAllMoviesByReleaseDate();
	}
	@GetMapping("/sortedByTrackingStatus")
	public DataResult<List<GetAllMoviesResponse>> getMoviesSortedByTrackingStatus() {
		return movieService.getMoviesSortedByTrackingStatus();
	}

}
