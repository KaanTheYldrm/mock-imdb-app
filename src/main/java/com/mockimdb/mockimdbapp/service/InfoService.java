package com.mockimdb.mockimdbapp.service;

import com.mockimdb.mockimdbapp.entity.Movie;
import com.mockimdb.mockimdbapp.request.CoincidenceRequest;

import java.util.List;

public interface InfoService {

    boolean isTypeCasted(String name);

    List<Movie> findCoincidences(CoincidenceRequest coincidenceRequest);

    Long findDegreeOfKevinBacon(String name);
}
