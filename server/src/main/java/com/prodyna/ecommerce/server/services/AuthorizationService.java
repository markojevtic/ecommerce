package com.prodyna.ecommerce.server.services;

import com.prodyna.ecommerce.server.repository.entity.Authorization;

public interface AuthorizationService {
    Authorization load(String username);

    Authorization insert(Authorization authorization);

    Authorization update(Authorization authorization);

    void delete(String username);
}
