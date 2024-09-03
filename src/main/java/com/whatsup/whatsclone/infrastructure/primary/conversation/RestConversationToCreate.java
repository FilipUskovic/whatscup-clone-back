package com.whatsup.whatsclone.infrastructure.primary.conversation;

import com.whatsup.whatsclone.infrastructure.primary.conversation.RestConversationToCreateBuilder;
import com.whatsup.whatsclone.messaging.domain.message.aggregate.ConversationToCreate;
import com.whatsup.whatsclone.messaging.domain.message.aggregate.ConversationToCreateBuilder;
import com.whatsup.whatsclone.messaging.domain.message.vo.ConversationName;
import com.whatsup.whatsclone.messaging.domain.user.valueobject.UserPublicId;
import org.jilt.Builder;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder
public record RestConversationToCreate(Set<UUID> members, String name) {
    public static ConversationToCreate toDomain(RestConversationToCreate restConversationToCreate) {
        RestConversationToCreateBuilder restConversationToCreateBuilder = RestConversationToCreateBuilder.restConversationToCreate();

        Set<UserPublicId> userUUIDs = restConversationToCreate.members
                .stream()
                .map(UserPublicId::new)
                .collect(Collectors.toSet());

        return ConversationToCreateBuilder.conversationToCreate()
                .name(new ConversationName(restConversationToCreate.name()))
                .members(userUUIDs)
                .build();
    }
}

