package com.MovieCatalogService.controller;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.MovieCatalogService.entity.Catalog;
import com.MovieCatalogService.entity.MovieInfo;
import com.MovieCatalogService.entity.Rating;
import com.MovieCatalogService.entity.UserRating;
import com.MovieCatalogService.service.MovieInfoFall;
import com.MovieCatalogService.service.UserRatingInfoFall;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {
	
	@Autowired
	private UserRatingInfoFall userRatingInfoFall;
	
	/*
	 * @Autowired private RestTemplate restTemplate;
	 */
	
	@Autowired
	private MovieInfoFall movieInfoFall;
	
	//@Autowired
	//private WebClient.Builder webClientBuilder;
	
	@GetMapping("/{userId}")
	public List<Catalog> getCatalog(@PathVariable String userId){
		
		//WebClient.Builder builder=WebClient.builder();
		//fake DB
		UserRating ratings=userRatingInfoFall.getUserRating(userId);			
				
		return ratings.getUserRating().stream().map(rating->movieInfoFall.getMovieInfo(rating)
			
			//for each movie id ,call movie info service and get details
			
			/*MovieInfo movie=webClientBuilder.build()
			.get()
			.uri("http://localhost:9002/movies/"+rating.getMovieId())
			.retrieve()
			.bodyToMono(MovieInfo.class)
			.block();*/
			
			//put them all together
			
		).collect(Collectors.toList());
 		
	}
	
}
