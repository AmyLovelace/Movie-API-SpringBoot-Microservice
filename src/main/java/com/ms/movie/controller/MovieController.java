package com.ms.movie.controller;

import com.ms.movie.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MovieController {
    @Value("${movie.service.url}")
    private String movieServiceUrl;

    @Bean
    public RestTemplate getTemplate() {
        return new RestTemplate();    }

    @Autowired
    public RestTemplate restTemplate;

    @GetMapping("/find/{id:\\d+}")
    @ResponseBody
    public Movie fetchMovie(@PathVariable int id) {

        Movie movie = restTemplate.exchange(movieServiceUrl+"all/"+id, HttpMethod.GET,null,Movie.class).getBody();
        return movie;
    }

    @GetMapping("/find")
    public Movie fetchAllMovies(){
        return restTemplate.exchange(movieServiceUrl+"all", HttpMethod.GET,null,Movie.class).getBody();
    }



}






