package com.whatsup.whatsclone.messaging.application;

import com.whatsup.whatsclone.messaging.domain.message.aggregate.Conversation;
import com.whatsup.whatsclone.messaging.domain.message.aggregate.ConversationToCreate;
import com.whatsup.whatsclone.messaging.domain.message.repository.ConversationRepository;
import com.whatsup.whatsclone.messaging.domain.message.repository.MessageRepository;
import com.whatsup.whatsclone.messaging.domain.message.service.ConversationCreator;
import com.whatsup.whatsclone.messaging.domain.message.service.ConversationDeleter;
import com.whatsup.whatsclone.messaging.domain.message.service.ConversationReaderService;
import com.whatsup.whatsclone.messaging.domain.message.service.MessageChangeNotifier;
import com.whatsup.whatsclone.messaging.domain.message.vo.ConversationPublicId;
import com.whatsup.whatsclone.messaging.domain.user.aggregate.User;
import com.whatsup.whatsclone.messaging.domain.user.repository.UserRepository;
import com.whatsup.whatsclone.messaging.domain.user.service.UserReader;
import com.whatsup.whatsclone.shared.service.State;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ConversationApplicationService {

    private final ConversationCreator conversationCreator;
    private final ConversationDeleter conversationDeleter;
    private final ConversationReaderService conversationReaderService;
    private final UserApplicationService userApplicationService;

// ovako instanicarmo neke klase jer nemaju na sebi anotacije npr @Service @Component ect
    public ConversationApplicationService(ConversationRepository conversationRepository,
                                          UserRepository userRepository,
                                          MessageChangeNotifier messageChangeNotifier,
                                          MessageRepository messageRepository,
                                          UserApplicationService userApplicationService) {
        UserReader userReader = new UserReader(userRepository);
        this.conversationCreator = new ConversationCreator(conversationRepository, userReader);
        this.conversationDeleter = new ConversationDeleter(conversationRepository, messageChangeNotifier);
        this.conversationReaderService = new ConversationReaderService(conversationRepository);
        this.userApplicationService = userApplicationService;
    }

    @Transactional
    public State<Conversation, String> create(ConversationToCreate conversation){
        User authenticatedUser = userApplicationService.getAuthenticatedUser();
        return conversationCreator.create(conversation, authenticatedUser);
    }

    @Transactional(readOnly = true)
    public List<Conversation> getAllByConnectedUser(Pageable pageable){
        User authenticatedUser = userApplicationService.getAuthenticatedUser();
        return this.conversationReaderService.getAllByUserPublicId(authenticatedUser.getUserPublicId(), pageable)
                .stream().toList();
    }

    @Transactional
    public State<ConversationPublicId, String> delete(ConversationPublicId conversationPublicId){
        User authenticatedUser = userApplicationService.getAuthenticatedUser();
        return this.conversationDeleter.deleteById(conversationPublicId, authenticatedUser);
    }

    @Transactional(readOnly = true)
    public Optional<Conversation> getOneByConversationId(ConversationPublicId conversationPublicId){
        User authenticatedUser = userApplicationService.getAuthenticatedUser();
        return this.conversationReaderService.getOneByPublicIdAndUserId(authenticatedUser.getUserPublicId(), conversationPublicId);
    }
}
