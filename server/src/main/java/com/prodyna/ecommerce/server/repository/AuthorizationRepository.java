package com.prodyna.ecommerce.server.repository;

import com.prodyna.ecommerce.server.repository.entity.Authorization;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorizationRepository extends MongoRepository<Authorization, String> {
}
