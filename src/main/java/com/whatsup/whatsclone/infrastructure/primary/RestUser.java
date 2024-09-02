package com.whatsup.whatsclone.infrastructure.primary;

import com.whatsup.whatsclone.infrastructure.primary.RestUserBuilder;
import com.whatsup.whatsclone.messaging.domain.user.aggregate.User;
import org.jilt.Builder;

import java.util.Set;
import java.util.UUID;

@Builder
public record RestUser(UUID publicId,
                       String firstName,
                       String lastName,
                       String email,
                       String imageUrl,
                       Set<RestAuthority> authorities) {

    static RestUser from (User user) {
        RestUserBuilder restUserBuilder = RestUserBuilder.restUser();
        if (user.getImageUrl() != null) {
            restUserBuilder.imageUrl(user.getImageUrl().value());
        }

        return restUserBuilder.email(user.getEmail().value())
                .firstName(user.getFirstName().value())
                .lastName(user.getLastName().value())
                .publicId(user.getUserPublicId().value())
                .authorities(RestAuthority.fromSet(user.getAuthorities()))
                .build();
    }
}
