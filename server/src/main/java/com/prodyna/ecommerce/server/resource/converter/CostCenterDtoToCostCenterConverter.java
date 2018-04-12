package com.prodyna.ecommerce.server.resource.converter;

import com.prodyna.ecommerce.server.repository.entity.CostCenter;
import com.prodyna.ecommerce.server.resource.dto.CostCenterDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CostCenterDtoToCostCenterConverter implements Converter<CostCenterDto, CostCenter> {
    @Override
    public CostCenter convert(CostCenterDto costCenterDto) {
        return CostCenter.builder()
                .costCenterId(costCenterDto.getCostCenterId())
                .name(costCenterDto.getName())
                .address(costCenterDto.getAddress())
                .contactList(costCenterDto.getContactList())
                .build();
    }
}
