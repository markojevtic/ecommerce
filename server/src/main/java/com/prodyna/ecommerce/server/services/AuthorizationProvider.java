package com.prodyna.ecommerce.server.services;

import com.prodyna.ecommerce.server.repository.entity.Authorization;
import com.prodyna.ecommerce.server.repository.entity.Role;
import com.prodyna.ecommerce.server.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AuthorizationProvider implements AuthenticationProvider {

    @Autowired
    UserService userService;

    @Autowired
    AuthorizationService authorizationService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String providedUsername = (String) authentication.getPrincipal();
        String providedPassword = (String) authentication.getCredentials();
        Optional<User> optionalUser = Optional.ofNullable(userService.load(providedUsername));

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<Role> userRoles = user.getRoles();
            Authorization authorization = authorizationService.load(user.getUsername());
            String password = authorization.getPassword();
            List<GrantedAuthority> grantedAuthorities = userRoles.stream()
                    .map(role -> new SimpleGrantedAuthority(role.name()))
                    .collect(Collectors.toList());

            if (user.isActive() && providedPassword.equals(password)) {
                return new UsernamePasswordAuthenticationToken(providedUsername, providedPassword, grantedAuthorities);

            } else throw new BadCredentialsException("Invalid username or Password");

        } else throw new BadCredentialsException("Invalid username or Password");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class == authentication;
    }
}
