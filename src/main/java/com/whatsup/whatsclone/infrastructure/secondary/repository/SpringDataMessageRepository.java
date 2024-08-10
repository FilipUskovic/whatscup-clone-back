package com.whatsup.whatsclone.infrastructure.secondary.repository;

import com.whatsup.whatsclone.messaging.domain.message.aggregate.Conversation;
import com.whatsup.whatsclone.messaging.domain.message.aggregate.Message;
import com.whatsup.whatsclone.messaging.domain.message.repository.MessageRepository;
import com.whatsup.whatsclone.messaging.domain.message.vo.ConversationPublicId;
import com.whatsup.whatsclone.messaging.domain.message.vo.MessageSentState;
import com.whatsup.whatsclone.messaging.domain.user.aggregate.User;
import com.whatsup.whatsclone.messaging.domain.user.valueobject.UserPublicId;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SpringDataMessageRepository implements MessageRepository {
    @Override
    public Message save(Message message, User sender, Conversation conversation) {
        return null;
    }

    @Override
    public int updateSentState(ConversationPublicId conversationPublicId, UserPublicId userPublicId, MessageSentState messageSentState) {
        return 0;
    }

    @Override
    public List<Message> findMessageToUpdate(ConversationPublicId conversationPublicId, UserPublicId userPublicId) {
        return List.of();
    }
}
