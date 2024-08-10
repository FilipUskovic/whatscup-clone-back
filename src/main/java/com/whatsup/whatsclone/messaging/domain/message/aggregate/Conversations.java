package com.whatsup.whatsclone.messaging.domain.message.aggregate;

import com.whatsup.whatsclone.shared.error.domain.Assert;

import java.util.List;

public record Conversations(List<Conversations> conversations) {
    public Conversations {
        Assert.field("conversations", conversations).notNull().noNullElement();
    }
}
