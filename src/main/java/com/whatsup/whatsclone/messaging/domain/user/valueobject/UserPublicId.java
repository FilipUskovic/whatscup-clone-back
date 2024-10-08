package com.whatsup.whatsclone.messaging.domain.user.valueobject;


import org.springframework.util.Assert;

import java.util.UUID;

public record UserPublicId(UUID value) {
    public UserPublicId {
        Assert.notNull(value, "value cannot be null");
    }
}
