package com.MovieCatalogService.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.MovieCatalogService.entity.Rating;
import com.MovieCatalogService.entity.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class UserRatingInfoFall {
	@Autowired
	private RestTemplate restTemplate;
	
	//CALL RATING-DATA-SERVICE SERVICE
	@HystrixCommand(fallbackMethod = "getFallbackUserRating")
	public UserRating getUserRating(String userId) {
		return restTemplate.getForObject("http://RATING-DATA-SERVICE/rating/users/"+userId,UserRating.class);
	}
	
	//fallback method for UserRating
	public UserRating getFallbackUserRating(String userId) {
		UserRating userRating=new UserRating();
		userRating.setUserRating(Arrays.asList(
				new Rating(userId,0)
				));
		return userRating;
	}
	
}

