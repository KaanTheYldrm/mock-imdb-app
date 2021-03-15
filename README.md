# mock-imdb-app
    acts as imdb api using imdb data set[]

# Requirements
    you should tsv files under resources/data

## About Project
    This project is created via SpringInitialzr

## Used Technologies
    JAVA 11
    Maven
    Spring Boot 2.4.3
    Spring Security
    Spring Data JPA
    Lombok
    Neo4J (Graph Database)

## To Run App
    - docker run --publish=7474:7474 --publish=7687:7687 -e 'NEO4J_AUTH=neo4j/secret' neo4j:4.1
    - run app