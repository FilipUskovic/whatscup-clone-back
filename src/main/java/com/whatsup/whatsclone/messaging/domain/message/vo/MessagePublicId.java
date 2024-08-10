package com.whatsup.whatsclone.messaging.domain.message.vo;

import org.springframework.util.Assert;

import java.util.UUID;

public record MessagePublicId(UUID value) {
    public MessagePublicId {
        Assert.notNull(value, "Id must not be null");
    }
}
