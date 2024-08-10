package com.whatsup.whatsclone.messaging.domain.message.aggregate;

import com.whatsup.whatsclone.messaging.domain.message.vo.ConversationPublicId;
import com.whatsup.whatsclone.messaging.domain.message.vo.MessageContent;

public record MessageSendNew(MessageContent messageContent, ConversationPublicId conversationPublicId) {

}
