package com.whatsup.whatsclone.messaging.domain.user.valueobject;

import com.whatsup.whatsclone.shared.error.domain.Assert;

public record UserLastName(String value) {
    public UserLastName {
        Assert.field(value, "value").maxLength(255);
    }
}
