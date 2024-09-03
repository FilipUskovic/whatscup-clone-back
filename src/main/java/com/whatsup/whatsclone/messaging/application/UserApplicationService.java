package com.whatsup.whatsclone.messaging.application;

import com.whatsup.whatsclone.messaging.domain.message.vo.ConversationPublicId;
import com.whatsup.whatsclone.messaging.domain.user.aggregate.User;
import com.whatsup.whatsclone.messaging.domain.user.repository.UserRepository;
import com.whatsup.whatsclone.messaging.domain.user.service.UserReader;
import com.whatsup.whatsclone.messaging.domain.user.service.UserSyncronizer;
import com.whatsup.whatsclone.messaging.domain.user.valueobject.UserEmail;
import com.whatsup.whatsclone.messaging.domain.user.valueobject.UserPublicId;
import com.whatsup.whatsclone.shared.authentication.application.AuthenticatedUser;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserApplicationService {

    private final UserSyncronizer userSyncronizer;
    private final UserReader userReader;
    private final UserRepository userRepository;

    public UserApplicationService(UserRepository userRepository, UserRepository userRepository1) {
        this.userSyncronizer = new UserSyncronizer(userRepository);
        this.userReader = new UserReader(userRepository);
        this.userRepository = userRepository1;
    }

    @Transactional
    public User getAuthenticatedUserWithSync(Jwt oauth2User, boolean forceResync) {
        userSyncronizer.syncWithIdp(oauth2User, forceResync);
        return userReader.getByEmail(new UserEmail(AuthenticatedUser.username().get()))
                .orElseThrow();
    }

    @Transactional(readOnly = true)
    public User getAuthenticatedUser() {
        return userReader.getByEmail(new UserEmail(AuthenticatedUser.username().get()))
                .orElseThrow();
    }

    @Transactional(readOnly = true)
    public List<User> search(Pageable pageable, String query) {
        return userReader.search(pageable, query).stream().toList();
    }

    public Optional<User> getByPublicId(UserPublicId publicId) {
        return userRepository.getOneByPublicId(publicId);
    }

    public List<User> findUsersToNotify(ConversationPublicId conversationPublicId, UserPublicId readerPublicId) {
        return userRepository.getRecipientByConversationIdExcludingReader(conversationPublicId, readerPublicId);
    }
}
