package com.mockimdb.mockimdbapp.config;

import com.mockimdb.mockimdbapp.entity.Movie;
import com.mockimdb.mockimdbapp.entity.Person;
import com.mockimdb.mockimdbapp.entity.Principal;
import com.mockimdb.mockimdbapp.repository.PersonRepository;
import com.mockimdb.mockimdbapp.repository.PrincipalRepository;
import com.mockimdb.mockimdbapp.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataInitializer {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    PersonRepository personRepository;

    @Autowired
    MovieService movieService;

    @Autowired
    PrincipalRepository principalRepository;

    @PostConstruct
    public void initData() {
        loadPrincipals();
        loadMovies();
        loadPersons();
    }

    private void loadMovies() {
        try {
            BufferedReader TSVReader = new BufferedReader(new FileReader(getFile("data/title.basics.tsv")));
            String line = null;
            while ((line = TSVReader.readLine()) != null) {
                String[] lineItems = line.split("\t");//splitting the line and adding its items in String[]
                movieService.save(
                        Movie.builder()
                                .id(lineItems[0])
                                .titleType(lineItems[1])
                                .primaryTitle(lineItems[2])
                                .originalTitle(lineItems[3])
                                .isAdult(lineItems[4])
                                .startYear(lineItems[5])
                                .endYear(lineItems[6])
                                .runtimeMinutes(lineItems[7])
                                .genres(lineItems[8])
                                .build());
            }
        } catch (Exception e) {
            logger.error("Movies not loaded");
        }
    }

    private void loadPersons() {
        personRepository.deleteAll();
        try {
            BufferedReader TSVReader = new BufferedReader(new FileReader(getFile("data/name.basics.tsv")));
            String line = null;
            while ((line = TSVReader.readLine()) != null) {
                String[] lineItems = line.split("\t");
                List<String> titleIds = principalRepository.getPrincipalByPersonId(lineItems[0])
                        .stream()
                        .map(Principal::getTitleId)
                        .collect(Collectors.toList());
                List<Movie> movies = movieService.getMoviesById(titleIds);
                personRepository.save(
                        Person.builder()
                                .id(lineItems[0])
                                .fullName(lineItems[1])
                                .born(lineItems[2])
                                .death(lineItems[3])
                                .title(lineItems[4])
                                .works(lineItems[5])
                                .movies(movies)
                                .build());
            }
        } catch (Exception e) {
            logger.error("Persons are not loaded {}", e);
        }
    }

    private void loadPrincipals() {
        principalRepository.deleteAll();
        try {
            BufferedReader TSVReader = new BufferedReader(new FileReader(getFile("data/title.principals.tsv")));
            String line = null;
            while ((line = TSVReader.readLine()) != null) {
                String[] lineItems = line.split("\t");//splitting the line and adding its items in String[]
                principalRepository.save(
                        Principal.builder()
                                .titleId(lineItems[0])
                                .personId(lineItems[1])
                                .build());
            }
        } catch (Exception e) {
            logger.error("Persons are not loaded {}", e);
        }
    }

    private File getFile(String filePath) {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(filePath).getFile());
    }

}
