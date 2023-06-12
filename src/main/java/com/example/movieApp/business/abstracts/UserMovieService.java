package com.example.movieApp.business.abstracts;

import java.util.List;

import com.example.movieApp.business.requests.CreateMovieRequest;
import com.example.movieApp.business.requests.UpdateMovieRequest;
import com.example.movieApp.business.responses.GetAllMoviesResponse;
import com.example.movieApp.core.utilities.results.DataResult;
import com.example.movieApp.core.utilities.results.Result;

public interface UserMovieService {

    DataResult<List<GetAllMoviesResponse>> getAll();	
	Result add(CreateMovieRequest createMovieRequest);
	Result update(UpdateMovieRequest updateMovieRequest, int id) throws Exception;
	Result delete(int id);
	
	DataResult<List<GetAllMoviesResponse>> getAllMoviesByReleaseDate();	//sıralama
	DataResult<List<GetAllMoviesResponse>> search(String keyword);//search
	DataResult<List<GetAllMoviesResponse>> trackingStatusFilter(boolean trackingStatus); //filtreleme
	
	//List<Movie> getMoviesSortedByPopularity(boolean trackingStatus);//izleme durumuna göre sıralama
	DataResult<List<GetAllMoviesResponse>> getMoviesSortedByTrackingStatus();
}
