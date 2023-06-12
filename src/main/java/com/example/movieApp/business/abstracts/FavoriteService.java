	package com.example.movieApp.business.abstracts;

import java.util.List;

import com.example.movieApp.business.requests.FavoriteRequest;
import com.example.movieApp.business.responses.GetAllFavoriesResponse;
import com.example.movieApp.core.utilities.results.DataResult;
import com.example.movieApp.core.utilities.results.Result;


public interface FavoriteService {

	DataResult<List<GetAllFavoriesResponse>> getAll();
    
	Result add(FavoriteRequest favoriteRequest);
	Result delete(int id);

}
