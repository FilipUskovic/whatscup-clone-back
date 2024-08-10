package com.whatsup.whatsclone.messaging.domain.user.valueobject;

import com.whatsup.whatsclone.shared.error.domain.Assert;

public record UserFirstName(String value) {
    public UserFirstName {
        Assert.field(value, "value").maxLength(255);
    }
}
