package com.mockimdb.mockimdbapp.repository;

import com.mockimdb.mockimdbapp.entity.Movie;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends Neo4jRepository<Movie, String> {

    @Query("MATCH (n:Movie) WHERE n.id IN $titleIds RETURN n")
    List<Movie> getMovieByIds(@Param("titleIds") List<String> titleIds);

    @Query("MATCH path = shortestPath((Bacon:Person {fullName:'Kevin Bacon'})-[*]-(Other:Person {fullName:$person})) " +
            "RETURN length(path)")
    Long degreeOfKevinBacon(@Param("person") String personName);
}
