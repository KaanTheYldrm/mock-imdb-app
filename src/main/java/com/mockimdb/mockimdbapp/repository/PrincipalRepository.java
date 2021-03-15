package com.mockimdb.mockimdbapp.repository;

import com.mockimdb.mockimdbapp.entity.Principal;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface PrincipalRepository extends Neo4jRepository<Principal, Long> {

    List<Principal> getPrincipalByTitleId(String titleId);

    List<Principal> getPrincipalByPersonId(String personId);

}
