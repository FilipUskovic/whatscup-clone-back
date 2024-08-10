package com.whatsup.whatsclone.messaging.domain.message.aggregate;

import com.whatsup.whatsclone.messaging.domain.message.vo.ConversationName;
import com.whatsup.whatsclone.messaging.domain.message.vo.ConversationPublicId;
import com.whatsup.whatsclone.messaging.domain.user.aggregate.User;
import com.whatsup.whatsclone.shared.error.domain.Assert;
import org.jilt.Builder;

import java.util.Set;

@Builder
public class Conversation {
    private final Set<Message> messages;
    private final Set<User> members;
    private final ConversationPublicId conversationPublicId;
    private final ConversationName conversationName;
    private Long dbId ;

    public Conversation(Set<Message> messages, Set<User> members, ConversationPublicId conversationPublicId, ConversationName conversationName, Long dbId) {
       assertMandatoryFields(members, conversationName);
        this.messages = messages;
        this.members = members;
        this.conversationPublicId = conversationPublicId;
        this.conversationName = conversationName;
        this.dbId = dbId;
    }

    private void assertMandatoryFields(Set<User> users, ConversationName conversationName) {
        Assert.notNull("users", users);
        Assert.notNull("conversationName", conversationName);
    }
    // generiramo samo gettere posto imamo final nema smisla setirati

    public Set<Message> getMessages() {
        return messages;
    }

    public Long getDbId() {
        return dbId;
    }

    public ConversationName getConversationName() {
        return conversationName;
    }

    public ConversationPublicId getConversationPublicId() {
        return conversationPublicId;
    }

    public Set<User> getMembers() {
        return members;
    }
}
