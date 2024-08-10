package com.whatsup.whatsclone.messaging.domain.message.aggregate;

import com.whatsup.whatsclone.shared.error.domain.Assert;

import java.util.List;

public record Messages(List<Messages> messages) {
    public Messages {
        Assert.field("messages", messages).notNull().noNullElement();
    }
}
