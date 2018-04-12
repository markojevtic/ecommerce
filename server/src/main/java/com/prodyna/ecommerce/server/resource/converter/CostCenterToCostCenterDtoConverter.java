package com.prodyna.ecommerce.server.resource.converter;

import com.prodyna.ecommerce.server.repository.entity.CostCenter;
import com.prodyna.ecommerce.server.resource.dto.CostCenterDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CostCenterToCostCenterDtoConverter implements Converter<CostCenter, CostCenterDto> {
    @Override
    public CostCenterDto convert(CostCenter costCenter) {
        return CostCenterDto.builder()
                .costCenterId(costCenter.getCostCenterId())
                .name(costCenter.getName())
                .address(costCenter.getAddress())
                .contactList(costCenter.getContactList())
                .build();
    }
}
