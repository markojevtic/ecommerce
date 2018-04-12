package com.prodyna.ecommerce.server.repository;

import com.prodyna.ecommerce.server.repository.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository  extends MongoRepository<User, String> {
}
