package com.mockimdb.mockimdbapp.service.impl;

import com.mockimdb.mockimdbapp.entity.Movie;
import com.mockimdb.mockimdbapp.repository.MovieRepository;
import com.mockimdb.mockimdbapp.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getMoviesById(List<String> titleIds) {
        return movieRepository.getMovieByIds(titleIds);
    }

    public Long findDegreeOfWithKevinBacon(String fullName) {
        return movieRepository.degreeOfKevinBacon(fullName);
    }

    public void save(Movie movie) {
        movieRepository.save(movie);
    }
}
