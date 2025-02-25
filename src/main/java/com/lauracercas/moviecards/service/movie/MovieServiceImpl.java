package com.lauracercas.moviecards.service.movie;


import com.lauracercas.moviecards.model.Movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Arrays;

/**
 * Autor: Laura Cercas Ramos
 * Proyecto: TFM Integración Continua con GitHub Actions
 * Fecha: 04/06/2024
 */
@Service
public class MovieServiceImpl implements MovieService {


    @Autowired
    RestTemplate template;
    String url = "https://moviecards-service-leiva.azurewebsites.net/movies";


    @Override
    public List<Movie> getAllMovies() {
        return Arrays.asList(template.getForObject(url, Movie[].class));
    }
    


    @Override
    public Movie save(Movie movie) {
        if (movie.getId() != null && movie.getId() > 0) {
            template.put(url, movie);
        } else {
            movie.setId(0);
            template.postForObject(url, movie, String.class);
        }
        return movie;
    }

    
    @Override
        public Movie getMovieById(Integer movieId) {
        return template.getForObject(url + "/" + movieId, Movie.class);
    }

}
