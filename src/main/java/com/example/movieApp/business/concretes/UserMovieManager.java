package com.example.movieApp.business.concretes;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.movieApp.business.abstracts.UserMovieService;
import com.example.movieApp.business.requests.CreateMovieRequest;
import com.example.movieApp.business.requests.UpdateMovieRequest;
import com.example.movieApp.business.responses.GetAllMoviesResponse;
import com.example.movieApp.business.rules.MovieBusinessRules;
import com.example.movieApp.core.utilities.results.DataResult;
import com.example.movieApp.core.utilities.results.ErrorResult;
import com.example.movieApp.core.utilities.results.Result;
import com.example.movieApp.core.utilities.results.SuccessDataResult;
import com.example.movieApp.core.utilities.results.SuccessResult;
import com.example.movieApp.dataAccess.abstracts.MovieRepository;
import com.example.movieApp.dataAccess.abstracts.UserRepository;
import com.example.movieApp.entities.Movie;
import com.example.movieApp.entities.User;

@Service
public class UserMovieManager implements UserMovieService {

	private final MovieRepository movieRepository;
	private final MovieBusinessRules movieBusinessRules;
	private final UserRepository userRepository;

	public UserMovieManager(MovieRepository movieRepository, MovieBusinessRules movieBusinessRules,
			UserRepository userRepository) {
		this.movieRepository = movieRepository;
		this.movieBusinessRules = movieBusinessRules;
		this.userRepository = userRepository;
	}

	@Override
	public Result add(CreateMovieRequest createMovieRequest) {

		Movie movie = new Movie();
		User user = userRepository.findById(createMovieRequest.getUserId())
				.orElseThrow(() -> new NoSuchElementException("Kullanıcı bulunamadı"));
		movie.setMovieName(createMovieRequest.getMovieName());
		movie.setMovieType(createMovieRequest.getMovieType());
		movie.setTrackingStatus(createMovieRequest.isTrackingStatus());
		movie.setReleaseDate(createMovieRequest.getReleaseDate());
		movie.setUser(user);
		this.movieRepository.save(movie);
		return new SuccessResult("Film eklendi");
	}

	@Override
	public Result delete(int id) {
		Boolean isIdExists = this.movieBusinessRules.idExists(id);

		if (isIdExists == null || isIdExists == false) {
			return new ErrorResult("id bulunamadı");
		}

		this.movieRepository.deleteById(id);
		return new SuccessResult("film silindi");
	}

	@Override
	public Result update(UpdateMovieRequest updateMovieRequest, int id) throws Exception {

		Movie movie = movieRepository.findById(id).orElseThrow(() -> new Exception("Film bulunamadi"));
		User user = userRepository.findById(updateMovieRequest.getUserId())
				.orElseThrow(() -> new NoSuchElementException("Kullanıcı bulunamadı"));
		movie.setMovieName(updateMovieRequest.getMovieName());
		movie.setMovieType(updateMovieRequest.getMovieType());
		movie.setTrackingStatus(updateMovieRequest.isTrackingStatus());
		movie.setReleaseDate(updateMovieRequest.getReleaseDate());
		movie.setUser(user);
		movieRepository.save(movie);
		return new SuccessResult("Güncellendi");

	}

	@Override
	public DataResult<List<GetAllMoviesResponse>> getAll() {
		List<Movie> movies = movieRepository.findAll();

		List<GetAllMoviesResponse> moviesResponse = movies.stream().map(movie -> {
			GetAllMoviesResponse responseItem = new GetAllMoviesResponse();
			responseItem.setId(movie.getId());
			responseItem.setMovieName(movie.getMovieName());
			responseItem.setMovieType(movie.getMovieType());
			responseItem.setTrackingStatus(movie.isTrackingStatus());
			responseItem.setReleaseDate(movie.getReleaseDate());
			responseItem.setEmail(movie.getUser().getEmail());
			return responseItem;
		}).collect(Collectors.toList());

		return new SuccessDataResult<>(moviesResponse, "listelendi");

	}

	@Override
	public DataResult<List<GetAllMoviesResponse>> search(String keyword) {// arama
		List<Movie> movies = movieRepository.search(keyword);

		List<GetAllMoviesResponse> moviesResponse = movies.stream().map(movie -> {
			GetAllMoviesResponse responseItem = new GetAllMoviesResponse();
			responseItem.setId(movie.getId());
			responseItem.setMovieName(movie.getMovieName());
			responseItem.setMovieType(movie.getMovieType());
			responseItem.setTrackingStatus(movie.isTrackingStatus());
			responseItem.setReleaseDate(movie.getReleaseDate());
			return responseItem;
		}).collect(Collectors.toList());

		return new SuccessDataResult<>(moviesResponse, "arama gerçekleştirildi");
	}

	@Override
	public DataResult<List<GetAllMoviesResponse>> trackingStatusFilter(boolean trackingStatus) {
		List<Movie> movies = movieRepository.findByTrackingStatus(trackingStatus);

		List<GetAllMoviesResponse> moviesResponse = movies.stream().map(movie -> {
			GetAllMoviesResponse responseItem = new GetAllMoviesResponse();
			responseItem.setId(movie.getId());
			responseItem.setMovieName(movie.getMovieName());
			responseItem.setMovieType(movie.getMovieType());
			responseItem.setTrackingStatus(movie.isTrackingStatus());
			responseItem.setReleaseDate(movie.getReleaseDate());
			responseItem.setEmail(movie.getUser().getEmail());
			return responseItem;
		}).collect(Collectors.toList());

		return new SuccessDataResult<>(moviesResponse, "Filtreleme gerçekleştirildi");
	}

	@Override
	public DataResult<List<GetAllMoviesResponse>> getAllMoviesByReleaseDate() {
		List<Movie> movies = movieRepository.findByOrderByReleaseDate();

		List<GetAllMoviesResponse> moviesResponse = movies.stream().map(movie -> {
			GetAllMoviesResponse responseItem = new GetAllMoviesResponse();
			responseItem.setId(movie.getId());
			responseItem.setMovieName(movie.getMovieName());
			responseItem.setMovieType(movie.getMovieType());
			responseItem.setTrackingStatus(movie.isTrackingStatus());
			responseItem.setReleaseDate(movie.getReleaseDate());
			responseItem.setEmail(movie.getUser().getEmail());
			return responseItem;
		}).collect(Collectors.toList());

		return new SuccessDataResult<>(moviesResponse, "Tarihe göre sıralandı");
	}

	@Override
	public DataResult<List<GetAllMoviesResponse>> getMoviesSortedByTrackingStatus() {
		Sort sort = Sort.by(Direction.DESC, "trackingStatus");
		 List<Movie> movies= movieRepository.findAll(sort);
		  List<GetAllMoviesResponse> movieResponses = movies.stream().map(movie -> {
		        GetAllMoviesResponse responseItem = new GetAllMoviesResponse();
				responseItem.setId(movie.getId());
				responseItem.setMovieName(movie.getMovieName());
				responseItem.setMovieType(movie.getMovieType());
				responseItem.setTrackingStatus(movie.isTrackingStatus());
				responseItem.setReleaseDate(movie.getReleaseDate());
				responseItem.setEmail(movie.getUser().getEmail());
				return responseItem;
		    }).collect(Collectors.toList());

		    return new SuccessDataResult<>(movieResponses, "İzleme durumuna göre sıralandı");
		
	}


}
