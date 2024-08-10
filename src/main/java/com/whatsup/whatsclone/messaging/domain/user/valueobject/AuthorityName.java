package com.whatsup.whatsclone.messaging.domain.user.valueobject;

import com.whatsup.whatsclone.shared.error.domain.Assert;

public record AuthorityName(String name) {

    public AuthorityName {
        Assert.field("name", name).notNull();
    }
}
