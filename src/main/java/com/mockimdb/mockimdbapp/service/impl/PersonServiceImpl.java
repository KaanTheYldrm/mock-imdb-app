package com.mockimdb.mockimdbapp.service.impl;

import com.mockimdb.mockimdbapp.entity.Person;
import com.mockimdb.mockimdbapp.repository.PersonRepository;
import com.mockimdb.mockimdbapp.service.PersonService;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public String getPersonId(String name) {
        Person person = personRepository.getPersonByFullName(name);
        return person.getId();
    }
}
