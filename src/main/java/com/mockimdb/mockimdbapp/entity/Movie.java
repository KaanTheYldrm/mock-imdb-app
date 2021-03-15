package com.mockimdb.mockimdbapp.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

import static org.springframework.data.neo4j.core.schema.Relationship.Direction.INCOMING;

@Node
@Getter
@Setter
@Builder
public class Movie {

    @Id
    private String id;

    private String titleType;

    private String primaryTitle;

    private String originalTitle;

    private String isAdult;

    private String startYear;

    private String endYear;

    private String runtimeMinutes;

    private String genres;

    @Relationship(type = "ACTED_IN", direction = INCOMING)
    private List<Person> actors;

    @Relationship(type = "DIRECTED", direction = INCOMING)
    private List<Person> directors;

    @Relationship(type = "WROTE", direction = INCOMING)
    private List<Person> writers;

    public void addActor(Person person) {
        actors.add(person);
    }
}
