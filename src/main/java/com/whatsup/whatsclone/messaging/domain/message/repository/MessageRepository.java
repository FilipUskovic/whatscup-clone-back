package com.whatsup.whatsclone.messaging.domain.message.repository;

import com.whatsup.whatsclone.messaging.domain.message.aggregate.Conversation;
import com.whatsup.whatsclone.messaging.domain.message.aggregate.Message;
import com.whatsup.whatsclone.messaging.domain.message.vo.ConversationPublicId;
import com.whatsup.whatsclone.messaging.domain.message.vo.MessageSentState;
import com.whatsup.whatsclone.messaging.domain.user.aggregate.User;
import com.whatsup.whatsclone.messaging.domain.user.valueobject.UserPublicId;

import java.util.List;

public interface MessageRepository {

    Message save(Message message, User sender, Conversation conversation);

    int updateSentState(ConversationPublicId conversationPublicId, UserPublicId userPublicId, MessageSentState messageSentState);

    List<Message> findMessageToUpdate(ConversationPublicId conversationPublicId, UserPublicId userPublicId);
}
