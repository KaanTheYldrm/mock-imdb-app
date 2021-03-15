package com.mockimdb.mockimdbapp.repository;

import com.mockimdb.mockimdbapp.entity.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface PersonRepository extends Neo4jRepository<Person, String> {

    Person getPersonByFullName(String name);
}
