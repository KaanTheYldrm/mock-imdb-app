package com.mockimdb.mockimdbapp.service.impl;

import com.mockimdb.mockimdbapp.entity.Movie;
import com.mockimdb.mockimdbapp.request.CoincidenceRequest;
import com.mockimdb.mockimdbapp.service.InfoService;
import com.mockimdb.mockimdbapp.service.MovieService;
import com.mockimdb.mockimdbapp.service.PersonService;
import com.mockimdb.mockimdbapp.service.PrincipalService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class InfoServiceImpl implements InfoService {

    private final PrincipalService principalService;

    private final PersonService personService;

    private final MovieService movieService;

    public InfoServiceImpl(PrincipalService principalService, PersonService personService, MovieService movieService) {
        this.principalService = principalService;
        this.personService = personService;
        this.movieService = movieService;
    }

    @Override
    public boolean isTypeCasted(String name) {
        String personId = personService.getPersonId(name);
        List<String> personMoviesId = principalService.getTitleIds(personId);
        List<Movie> movies = movieService.getMoviesById(personMoviesId);

        Map<String, Long> countedMap = movies.stream()
                .collect(Collectors.groupingBy(Movie::getGenres, Collectors.counting()));

        int max = Collections.max(countedMap.values()).intValue();
        int movieCount = movies.size();

        return max > (movieCount/2);
    }

    @Override
    public List<Movie> findCoincidences(CoincidenceRequest coincidenceRequest) {
        String firstPersonId = personService.getPersonId(coincidenceRequest.getFirstPerson());
        String secondPersonId = personService.getPersonId(coincidenceRequest.getSecondPerson());
        List<String> firstPersonMoviesId = principalService.getTitleIds(firstPersonId);
        List<String> secondPersonMoviesId = principalService.getTitleIds(secondPersonId);
        List<String> sameMovies = new ArrayList<>();
        Set<String> movieSet = new HashSet<>();

        firstPersonMoviesId
                .forEach(movie -> {
                    if(!movieSet.add(movie)) {
                        sameMovies.add(movie);
                    }
                });

        secondPersonMoviesId
                .forEach(movie -> {
                    if(!movieSet.add(movie)) {
                        sameMovies.add(movie);
                    }
                });

        return movieService.getMoviesById(sameMovies);
    }

    @Override
    public Long findDegreeOfKevinBacon(String name) {
        return movieService.findDegreeOfWithKevinBacon(name);
    }
}
