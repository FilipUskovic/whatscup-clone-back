package com.whatsup.whatsclone.messaging.domain.user.valueobject;

import com.whatsup.whatsclone.shared.error.domain.Assert;

public record UserEmail(String value) {
    public UserEmail {
        Assert.field(value, "value").maxLength(255);
    }
}
