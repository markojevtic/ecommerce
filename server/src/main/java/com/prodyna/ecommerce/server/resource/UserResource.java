package com.prodyna.ecommerce.server.resource;

import com.prodyna.ecommerce.server.repository.entity.Category;
import com.prodyna.ecommerce.server.repository.entity.Product;
import com.prodyna.ecommerce.server.repository.entity.User;
import com.prodyna.ecommerce.server.resource.dto.CategoryDto;
import com.prodyna.ecommerce.server.resource.dto.ProductDto;
import com.prodyna.ecommerce.server.resource.dto.UserDto;
import com.prodyna.ecommerce.server.services.UserService;
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
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/users", produces = APPLICATION_JSON_UTF8_VALUE)
public class UserResource {

    @Autowired
    private UserService userService;

    @Autowired
    private ConversionService conversionService;

    public static final ControllerLinkBuilder createResourceLink() {
        return linkTo(UserResource.class);
    }

    public static final ControllerLinkBuilder createResourceSingleLink(String id) {
        return linkTo(methodOn(UserResource.class).load(id));
    }

    @RequestMapping(method = GET, path = "/{id}")
    public ResponseEntity<UserDto> load(@PathVariable String id) {
        User user = userService.load(id);
        UserDto userDto = conversionService.convert(user, UserDto.class);

        return ResponseEntity.ok(userDto);
    }

    @RequestMapping(method = GET)
    public ResponseEntity<List<UserDto>> getAll() {
        List<User> users = userService.getAll();

        TypeDescriptor typeDscUsers = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(User.class));
        TypeDescriptor typeDscUserDtos = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(UserDto.class));

        List<UserDto> userDtos = (List<UserDto>) conversionService.convert(users, typeDscUsers, typeDscUserDtos);

        return ResponseEntity.ok(userDtos);
    }

    @RequestMapping(method = POST)
    public ResponseEntity<UserDto> insert(@RequestBody UserDto userDto) {
        User user = conversionService.convert(userDto, User.class);
        User insertedUser = userService.insert(user);

        return ResponseEntity.ok(conversionService.convert(insertedUser, UserDto.class));
    }

    @RequestMapping(method = POST, path = "/update")
    public ResponseEntity<UserDto> update(@RequestBody UserDto userDto) {
        User user = conversionService.convert(userDto, User.class);
        User updatedUser = userService.update(user);

        return ResponseEntity.ok(conversionService.convert(updatedUser, UserDto.class));
    }

    @RequestMapping(method = DELETE, path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
