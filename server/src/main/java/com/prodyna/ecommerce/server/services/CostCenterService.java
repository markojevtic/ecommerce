package com.prodyna.ecommerce.server.services;

import com.prodyna.ecommerce.server.repository.entity.CostCenter;

import java.util.List;

public interface CostCenterService {
    CostCenter load(String id);

    List<CostCenter> getAll();

    void delete(String id);

    CostCenter insert(CostCenter customer);

    CostCenter update(CostCenter customer);
}
