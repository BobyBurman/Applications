package com.MovieInfoService.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MovieInfoService.entity.MovieInfo;

@RestController
@RequestMapping("/movies")
public class MovieInfoController {
	
	@GetMapping("/{movieId}")
	public MovieInfo getMovieInfo(@PathVariable("movieId") String movieId) {
		return new MovieInfo(movieId,"radhe");
	}
}
