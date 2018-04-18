package com.prodyna.ecommerce.server.resource;

import com.prodyna.ecommerce.server.repository.entity.Authorization;
import com.prodyna.ecommerce.server.resource.dto.AuthorizationDto;
import com.prodyna.ecommerce.server.services.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(value = "/authorizations", produces = APPLICATION_JSON_UTF8_VALUE)
public class AuthorizationResource {


    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private ConversionService conversionService;

    public static final ControllerLinkBuilder createResourceLink() {
        return linkTo(AuthorizationResource.class);
    }

    public static final ControllerLinkBuilder createResourceSingleLink(String id) {
        return linkTo(methodOn(AuthorizationResource.class).load(id));
    }

    @RequestMapping(method = GET, path = "/{username}")
    public ResponseEntity<AuthorizationDto> load(@PathVariable String username) {
        Authorization authorization = authorizationService.load(username);
        AuthorizationDto authorizationDto = conversionService.convert(authorization, AuthorizationDto.class);

        return ResponseEntity.ok(authorizationDto);
    }

    @RequestMapping(method = POST)
    public ResponseEntity<AuthorizationDto> insert(@RequestBody AuthorizationDto authorizationDto) {
        Authorization authorization = conversionService.convert(authorizationDto, Authorization.class);
        Authorization insertedAuthorization = authorizationService.insert(authorization);

        return ResponseEntity.ok(conversionService.convert(insertedAuthorization, AuthorizationDto.class));
    }

    @RequestMapping(method = POST, path = "/update")
    public ResponseEntity<AuthorizationDto> update(@RequestBody AuthorizationDto authorizationDto) {
        Authorization authorization = conversionService.convert(authorizationDto, Authorization.class);
        Authorization updatedAuthorization = authorizationService.update(authorization);

        return ResponseEntity.ok(conversionService.convert(updatedAuthorization, AuthorizationDto.class));
    }

    @RequestMapping(method = DELETE, path = "/{username}")
    public ResponseEntity<Void> delete(@PathVariable String username) {
        authorizationService.delete(username);

        return ResponseEntity.noContent().build();
    }
}
