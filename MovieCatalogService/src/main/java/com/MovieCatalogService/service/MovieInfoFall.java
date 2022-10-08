package com.MovieCatalogService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.MovieCatalogService.entity.Catalog;
import com.MovieCatalogService.entity.MovieInfo;
import com.MovieCatalogService.entity.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class MovieInfoFall {
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getFallbackMovieInfo")
	public Catalog getMovieInfo(Rating rating) {
		MovieInfo movie=restTemplate.getForObject("http://MOVIE-INFO-SERVICE/movies/"+rating.getMovieId(),MovieInfo.class);
		return new Catalog(movie.getName(),"Test",rating.getRating()); 
	}
	
	
	//fallback method for UserRating
	public Catalog getFallbackMovieInfo(Rating rating) {
		
		return new Catalog("Movie NAME Not Found","Nothing",rating.getRating());
	}
	
}
