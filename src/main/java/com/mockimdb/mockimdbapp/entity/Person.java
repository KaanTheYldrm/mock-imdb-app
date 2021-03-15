package com.mockimdb.mockimdbapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

import static org.springframework.data.neo4j.core.schema.Relationship.Direction.OUTGOING;

@Node
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Person {

    @Id
    private String id;

    private String fullName;

    private String born;

    private String death;

    private String title;

    private String works;

    @Relationship(type = "ACTED_IN")
    private List<Movie> movies;

    @Relationship(type = "DIRECTED", direction = OUTGOING)
    private List<Movie> directors;

    @Relationship(type = "WROTE", direction = OUTGOING)
    private List<Movie> writers;

}
