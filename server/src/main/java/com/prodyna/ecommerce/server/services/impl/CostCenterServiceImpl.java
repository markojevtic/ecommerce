package com.prodyna.ecommerce.server.services.impl;

import com.prodyna.ecommerce.server.exception.EntityNotFoundException;
import com.prodyna.ecommerce.server.repository.CostCenterRepository;
import com.prodyna.ecommerce.server.repository.entity.CostCenter;
import com.prodyna.ecommerce.server.services.CostCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CostCenterServiceImpl implements CostCenterService {

    @Autowired
    private CostCenterRepository costCenterRepository;


    @Override
    public CostCenter insert(CostCenter costCenter) {
        return costCenterRepository.insert(costCenter);
    }

    @Override
    public CostCenter update(CostCenter customer) {
        return costCenterRepository.save(customer);
    }

    @Override
    public CostCenter load(String id) {
        return costCenterRepository.findById(id).orElseThrow(() -> EntityNotFoundException.createExceptionByEntityAndId(CostCenter.class, id));
    }

    @Override
    public List<CostCenter> getAll() {
        return costCenterRepository.findAll();
    }

    @Override
    public void delete(String id) {
        costCenterRepository.deleteById(id);
    }

}
