package com.prodyna.ecommerce.server.resource;

import com.prodyna.ecommerce.server.repository.entity.CostCenter;
import com.prodyna.ecommerce.server.resource.dto.CostCenterDto;
import com.prodyna.ecommerce.server.services.CostCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(value = "/costCenters", produces = APPLICATION_JSON_UTF8_VALUE)
public class CostCenterResource {

    @Autowired(required = false)
    private CostCenterService costCenterService;

    @Autowired
    private ConversionService conversionService;

    public static final ControllerLinkBuilder createLink() {
        return linkTo(CostCenterResource.class);
    }

    public static final ControllerLinkBuilder createSingleLink(String id) {
        return linkTo(methodOn(CostCenterResource.class).load(id));
    }

    @RequestMapping(method = GET, path = "/{id}")
    public ResponseEntity<CostCenterDto> load(@PathVariable String id) {
        CostCenter costCenter = costCenterService.load(id);
        CostCenterDto costCenterDto = conversionService.convert(costCenter, CostCenterDto.class);

        return ResponseEntity.ok(costCenterDto);
    }

    @RequestMapping(method = GET)
    public ResponseEntity<List<CostCenterDto>> getAll() {
        List<CostCenter> costCenters = costCenterService.getAll();

        TypeDescriptor typeDscCostCenter = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(CostCenter.class));
        TypeDescriptor typeDscCostCenterDto = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(CostCenterDto.class));

        List<CostCenterDto> costCentersDtos = (List<CostCenterDto>) conversionService.convert(costCenters, typeDscCostCenter, typeDscCostCenterDto);

        return ResponseEntity.ok(costCentersDtos);
    }

    @RequestMapping(method = POST)
    public ResponseEntity<CostCenterDto> insert(@RequestBody CostCenterDto costCenterDto) {
        CostCenter costCenter = conversionService.convert(costCenterDto, CostCenter.class);
        CostCenter insertedCostCenter = costCenterService.insert(costCenter);

        return ResponseEntity.ok(conversionService.convert(insertedCostCenter, CostCenterDto.class));
    }

    @RequestMapping(method = POST, path = "/update")
    public ResponseEntity<CostCenterDto> update(@RequestBody CostCenterDto costCenterDto) {
        CostCenter costCenter = conversionService.convert(costCenterDto, CostCenter.class);
        CostCenter updatedCostCenter = costCenterService.update(costCenter);

        return ResponseEntity.ok(conversionService.convert(updatedCostCenter, CostCenterDto.class));
    }

    @RequestMapping(method = DELETE, path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        costCenterService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
