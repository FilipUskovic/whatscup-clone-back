package com.whatsup.whatsclone.messaging.domain.message.vo;

import com.whatsup.whatsclone.shared.error.domain.Assert;

public record ConversationName(String name) {

    public ConversationName {
        Assert.field("name", name).minLength(3).maxLength(255);
    }
}
