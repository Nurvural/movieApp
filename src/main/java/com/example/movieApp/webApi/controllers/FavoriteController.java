package com.example.movieApp.webApi.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.movieApp.business.abstracts.FavoriteService;
import com.example.movieApp.business.requests.FavoriteRequest;
import com.example.movieApp.business.responses.GetAllFavoriesResponse;
import com.example.movieApp.core.utilities.results.DataResult;
import com.example.movieApp.core.utilities.results.Result;

@RestController 
@RequestMapping("/api/favories") 
public class FavoriteController {

	private final FavoriteService favoriteService;

	public FavoriteController(FavoriteService favoriteService) {
		super();
		this.favoriteService = favoriteService;
	}
	@GetMapping("/getall")
	public DataResult<List<GetAllFavoriesResponse>> getAll() {
		return favoriteService.getAll();
	}
	
	@PostMapping()
	@ResponseStatus(code = HttpStatus.CREATED)
	public void add(@RequestBody() @Valid FavoriteRequest favoriteRequest) {
		this.favoriteService.add(favoriteRequest);
	}
	@DeleteMapping("/{id}")
	public Result delete(@PathVariable int id  ) {
		return this.favoriteService.delete(id);
	}
}
