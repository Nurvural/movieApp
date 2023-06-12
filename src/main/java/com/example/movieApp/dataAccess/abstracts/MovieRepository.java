package com.example.movieApp.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.movieApp.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

	@Query("SELECT m FROM Movie m WHERE m.movieType LIKE %?1%"
			+  " OR m.movieName LIKE %?1%")
	List<Movie> search(String keyword);

	List<Movie> findByTrackingStatus(boolean trackingStatus);// filtreleme
	List<Movie> findByOrderByReleaseDate();
	//boolean existsById(int id);

	//  List<Movie> findByOrderByTrackingStatusDesc(boolean trackingStatus);



}
