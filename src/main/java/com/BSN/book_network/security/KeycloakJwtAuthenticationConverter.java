package com.BSN.book_network.security;


import jakarta.validation.constraints.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

public class KeycloakJwtAuthenticationConverter implements Converter<Jwt ,AbstractAuthenticationToken> {

    @Override
    public AbstractAuthenticationToken convert( @NotNull Jwt source) {

        return new JwtAuthenticationToken(
                source,
                Stream.concat(
                        new JwtGrantedAuthoritiesConverter().convert(source).stream(),
                        extractResourceRoles(source).stream()).collect(toSet()));
    }

    private Collection<? extends GrantedAuthority> extractResourceRoles(@NotNull Jwt jwt) {
        var resourceAccess = new HashMap<>(jwt.getClaim("resource_access"));

        var eternal = (Map<String , List<String >>) resourceAccess.get("account");

        var roles = eternal.get("roles");

    return roles.stream().map(role-> new SimpleGrantedAuthority("ROLE_" + role.replace("-" , "_"))).collect(toSet());
    }

}
