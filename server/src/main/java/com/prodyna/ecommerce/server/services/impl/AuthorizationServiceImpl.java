package com.prodyna.ecommerce.server.services.impl;

import com.prodyna.ecommerce.server.exception.EntityNotFoundException;
import com.prodyna.ecommerce.server.repository.AuthorizationRepository;
import com.prodyna.ecommerce.server.repository.entity.Authorization;
import com.prodyna.ecommerce.server.services.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    AuthorizationRepository authorizationRepository;

    @Override
    public Authorization load(String username) {
        return authorizationRepository.findById(username).orElseThrow(() ->
                EntityNotFoundException.createExceptionByEntityAndId(Authorization.class, username));
    }

    @Override
    public Authorization insert(Authorization authorization) {
        return authorizationRepository.insert(authorization);
    }

    @Override
    public Authorization update(Authorization authorization) {
        return authorizationRepository.save(authorization);
    }

    @Override
    public void delete(String username) {
        authorizationRepository.deleteById(username);
    }
}
