package com.example.movieApp.business.concretes;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.movieApp.business.abstracts.FavoriteService;
import com.example.movieApp.business.requests.FavoriteRequest;
import com.example.movieApp.business.responses.GetAllFavoriesResponse;
import com.example.movieApp.core.utilities.results.DataResult;
import com.example.movieApp.core.utilities.results.Result;
import com.example.movieApp.core.utilities.results.SuccessDataResult;
import com.example.movieApp.core.utilities.results.SuccessResult;
import com.example.movieApp.dataAccess.abstracts.FavoriteRepository;
import com.example.movieApp.dataAccess.abstracts.MovieRepository;
import com.example.movieApp.dataAccess.abstracts.UserRepository;
import com.example.movieApp.entities.Favorite;
import com.example.movieApp.entities.Movie;
import com.example.movieApp.entities.User;

@Service
public class FavoriteManager implements FavoriteService {

	private final FavoriteRepository favoriteRepository;
	private final MovieRepository movieRepository;
	private final UserRepository userRepository;

	


	public FavoriteManager(FavoriteRepository favoriteRepository, MovieRepository movieRepository,
			UserRepository userRepository) {
		super();
		this.favoriteRepository = favoriteRepository;
		this.movieRepository = movieRepository;
		this.userRepository = userRepository;
	}

	@Override
	public DataResult<List<GetAllFavoriesResponse>> getAll() {
		List<Favorite> favorites = favoriteRepository.findAll();

		List<GetAllFavoriesResponse> favoritesResponses = favorites.stream().map(favorite -> {
			GetAllFavoriesResponse responseItem = new GetAllFavoriesResponse();
			responseItem.setId(favorite.getId());
			responseItem.setMovieName(favorite.getMovie().getMovieName());
			responseItem.setMovieType(favorite.getMovie().getMovieType());
			responseItem.setTrackingStatus(favorite.getMovie().isTrackingStatus());
			responseItem.setReleaseDate(favorite.getMovie().getReleaseDate());
			responseItem.setEmail(favorite.getUser().getEmail());
			return responseItem;
		}).collect(Collectors.toList());

		return new SuccessDataResult<>(favoritesResponses, "Favoriler listelendi");
	}

	@Override
	public Result add(FavoriteRequest favoriteRequest) {
		Favorite favorite = new Favorite();
		Movie movie = movieRepository.findById(favoriteRequest.getMovieId())
				.orElseThrow(() -> new NoSuchElementException("Film bulunamadı"));
		User user = userRepository.findById(favoriteRequest.getUserId())
				.orElseThrow(() -> new NoSuchElementException("kullanıcı bulunamadı"));
		favorite.setMovie(movie);
		favorite.setUser(user);
		
		favoriteRepository.save(favorite);
		return new SuccessResult("Favorilere eklendi");
	}

	@Override
	public Result delete(int id) {

		this.favoriteRepository.deleteById(id);
		return new SuccessResult("Favorilerden kaldırıldı");
	}

}
