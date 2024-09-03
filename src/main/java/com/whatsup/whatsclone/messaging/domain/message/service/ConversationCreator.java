package com.whatsup.whatsclone.messaging.domain.message.service;

import com.whatsup.whatsclone.messaging.domain.message.aggregate.Conversation;
import com.whatsup.whatsclone.messaging.domain.message.aggregate.ConversationToCreate;
import com.whatsup.whatsclone.messaging.domain.message.repository.ConversationRepository;
import com.whatsup.whatsclone.messaging.domain.user.aggregate.User;
import com.whatsup.whatsclone.messaging.domain.user.service.UserReader;
import com.whatsup.whatsclone.messaging.domain.user.valueobject.UserPublicId;
import com.whatsup.whatsclone.shared.service.State;

import java.util.List;
import java.util.Optional;

public class ConversationCreator {

    private final ConversationRepository conversationRepository;
    private final UserReader userReader;

    public ConversationCreator(ConversationRepository conversationRepository, UserReader userReader) {
        this.conversationRepository = conversationRepository;
        this.userReader = userReader;
    }

    public State<Conversation, String> create(ConversationToCreate newCoversation, User authenticatedUser){
        newCoversation.getMembers().add(authenticatedUser.getUserPublicId());
        List<User> members = userReader.getUsersByPublicId(newCoversation.getMembers());
        List<UserPublicId> memberUUIds = members.stream().map(User::getUserPublicId).toList();
        Optional<Conversation> conversationAlredyPresent = conversationRepository.getConversationByUserPublicIds(memberUUIds);
        State<Conversation, String> stateResult;
        if(conversationAlredyPresent.isPresent()){
            Conversation saved = conversationRepository.save(newCoversation, members);
            stateResult = State.<Conversation, String>builder().forSuccess(saved);
        }else {
            stateResult = State.<Conversation, String>builder().forError("Conversation Already Exits");
        }
        return stateResult;
    }
}
