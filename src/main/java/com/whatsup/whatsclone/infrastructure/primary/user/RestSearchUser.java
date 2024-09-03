package com.whatsup.whatsclone.infrastructure.primary.user;

import com.whatsup.whatsclone.infrastructure.primary.user.RestSearchUserBuilder;
import com.whatsup.whatsclone.messaging.domain.user.aggregate.User;
import org.jilt.Builder;

import java.util.UUID;

@Builder
public record RestSearchUser(String lastName,
                             String firstName,
                             String email,
                             UUID publicId,
                             String imageUrl) {
    public static RestSearchUser from(User user) {
        RestSearchUserBuilder restSearchUserBuilder = RestSearchUserBuilder.restSearchUser();

        if (user.getLastName() != null) {
            restSearchUserBuilder.lastName(user.getLastName().value());
        }

        if (user.getFirstName() != null) {
            restSearchUserBuilder.firstName(user.getFirstName().value());
        }

        if (user.getImageUrl() != null) {
            restSearchUserBuilder.imageUrl(user.getImageUrl().value());
        }

        return restSearchUserBuilder.email(user.getEmail().value())
                .publicId(user.getUserPublicId().value())
                .build();
    }

}

