package com.prodyna.ecommerce.server.repository;

import com.prodyna.ecommerce.server.repository.entity.CostCenter;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CostCenterRepository extends MongoRepository<CostCenter, String> {
}
