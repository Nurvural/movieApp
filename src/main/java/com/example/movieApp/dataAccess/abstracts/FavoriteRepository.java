package com.example.movieApp.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.movieApp.entities.Favorite;;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

}
