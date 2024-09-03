package com.whatsup.whatsclone.infrastructure.primary.conversation;

import com.whatsup.whatsclone.infrastructure.primary.conversation.RestConversationBuilder;
import com.whatsup.whatsclone.infrastructure.primary.message.RestMessage;
import com.whatsup.whatsclone.messaging.domain.message.aggregate.Conversation;
import org.jilt.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record RestConversation(
        UUID publicId,
        String name,
        List<RestUserForConversation> members,
        List<RestMessage> messages
) {

    public static RestConversation from (Conversation conversation){
        RestConversationBuilder restConversationBuilder = RestConversationBuilder.restConversation()
                .publicId(conversation.getConversationPublicId().value())
                .name(conversation.getConversationName().name())
                .members(RestUserForConversation.from(conversation.getMembers()));
        if(conversation.getMessages() != null){
            restConversationBuilder.messages(RestMessage.from(conversation.getMessages()));
        }
        return restConversationBuilder.build();
    }
}
