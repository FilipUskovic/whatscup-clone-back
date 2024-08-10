package com.whatsup.whatsclone.messaging.domain.message.vo;

import com.whatsup.whatsclone.shared.error.domain.Assert;

import java.time.Instant;

public record MessageSentTime(Instant date) {
    public MessageSentTime {
        Assert.field("date", date).notNull();
    }
}
