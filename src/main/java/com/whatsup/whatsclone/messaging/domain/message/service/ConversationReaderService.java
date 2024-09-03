package com.whatsup.whatsclone.messaging.domain.message.service;

import com.whatsup.whatsclone.messaging.domain.message.aggregate.Conversation;
import com.whatsup.whatsclone.messaging.domain.message.repository.ConversationRepository;
import com.whatsup.whatsclone.messaging.domain.message.vo.ConversationPublicId;
import com.whatsup.whatsclone.messaging.domain.user.valueobject.UserPublicId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class ConversationReaderService {

    private final ConversationRepository conversationRepository;

    public ConversationReaderService(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    public Page<Conversation> getAllByUserPublicId(UserPublicId userPublicId, Pageable pageable){
        return conversationRepository.getConversationByUserPublicId(userPublicId, pageable);
    }

    public Optional<Conversation> getOneByPublicId(ConversationPublicId conversationPublicId){
        return conversationRepository.getOneByPublicId(conversationPublicId);
    }

    public Optional<Conversation> getOneByPublicIdAndUserId(UserPublicId userPublicId, ConversationPublicId conversationPublicId){
        return conversationRepository.getConversationByUsersPublicIdAndPublicId(userPublicId, conversationPublicId);
    }




}
