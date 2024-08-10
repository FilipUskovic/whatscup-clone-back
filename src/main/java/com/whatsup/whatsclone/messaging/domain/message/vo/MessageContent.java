package com.whatsup.whatsclone.messaging.domain.message.vo;

public record MessageContent(String text,
                             MessageType type,
                             MessageMediaContent media) {
}


