package com.whatsup.whatsclone.shared.authentication.infrastructure.primary;

import com.whatsup.whatsclone.shared.authentication.application.AuthenticatedUser;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KeyCloakJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    // ova klasa je sazluzna za dohvacanje rola is keycloak-a i konvertiranje u nesto sto spring securitiy razumije

    // ova klasa se konveritarati access token koji dolazi iz web request-a
    @Override
    public AbstractAuthenticationToken convert(@NonNull Jwt source) {
        return new JwtAuthenticationToken(source, Stream.concat
                (new JwtGrantedAuthoritiesConverter().convert(source).stream(),
                        extractResourcesRoles(source).stream()).collect(Collectors.toSet()));
    }

    private Collection<? extends GrantedAuthority> extractResourcesRoles(Jwt jwt) {
        return AuthenticatedUser.extractRolesFromToken(jwt).stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }
}
