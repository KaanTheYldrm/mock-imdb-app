package com.mockimdb.mockimdbapp.service;

import com.mockimdb.mockimdbapp.entity.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> getMoviesById(List<String> titleIds);

    void save(Movie movie);

    Long findDegreeOfWithKevinBacon(String fullName);
}
