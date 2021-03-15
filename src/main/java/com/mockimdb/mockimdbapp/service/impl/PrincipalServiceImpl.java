package com.mockimdb.mockimdbapp.service.impl;

import com.mockimdb.mockimdbapp.entity.Principal;
import com.mockimdb.mockimdbapp.repository.PrincipalRepository;
import com.mockimdb.mockimdbapp.service.PrincipalService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrincipalServiceImpl implements PrincipalService {

    private final PrincipalRepository principalRepository;

    public PrincipalServiceImpl(PrincipalRepository principalRepository) {
        this.principalRepository = principalRepository;
    }

    public List<String> getTitleIds(String personId) {
        return principalRepository.getPrincipalByPersonId(personId)
                .stream()
                .map(Principal::getTitleId)
                .collect(Collectors.toList());
    }
}
