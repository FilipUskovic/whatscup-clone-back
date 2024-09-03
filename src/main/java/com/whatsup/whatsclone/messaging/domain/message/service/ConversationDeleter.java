package com.whatsup.whatsclone.messaging.domain.message.service;

import com.whatsup.whatsclone.messaging.domain.message.aggregate.Conversation;
import com.whatsup.whatsclone.messaging.domain.message.repository.ConversationRepository;
import com.whatsup.whatsclone.messaging.domain.message.vo.ConversationPublicId;
import com.whatsup.whatsclone.messaging.domain.user.aggregate.User;
import com.whatsup.whatsclone.messaging.domain.user.valueobject.UserPublicId;
import com.whatsup.whatsclone.shared.service.State;

import java.util.Optional;

public class ConversationDeleter {

    public final ConversationRepository conversationRepository;
    private final MessageChangeNotifier messageChangeNotifier;

    public ConversationDeleter(ConversationRepository conversationRepository, MessageChangeNotifier messageChangeNotifier) {
        this.conversationRepository = conversationRepository;
        this.messageChangeNotifier = messageChangeNotifier;
    }

    public State<ConversationPublicId, String> deleteById(ConversationPublicId conversationPublicId, User connectedUser){
        State<ConversationPublicId, String> stateResults;
        // provjeravamo dali razogovr priprada ogovarajucem user-u
        Optional<Conversation> conversationToDelete
                = this.conversationRepository.getConversationByUsersPublicIdAndPublicId(connectedUser.getUserPublicId(), conversationPublicId);
        if(conversationToDelete.isPresent()){
            this.conversationRepository.deleteByPublicId(connectedUser.getUserPublicId(), conversationPublicId);
            this.messageChangeNotifier.delete(conversationPublicId, conversationToDelete.get().getMembers()
                    .stream().map(User::getUserPublicId).toList());
           stateResults =  State.<ConversationPublicId, String>builder().forSuccess(conversationPublicId);
        } else {
            stateResults =  State.<ConversationPublicId, String>builder().forError("This conversation doesnt exist");
        }
        return stateResults;
    }
}
