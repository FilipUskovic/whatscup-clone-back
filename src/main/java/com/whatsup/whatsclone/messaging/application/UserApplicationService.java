package com.whatsup.whatsclone.messaging.application;

import com.whatsup.whatsclone.messaging.domain.user.aggregate.User;
import com.whatsup.whatsclone.messaging.domain.user.repository.UserRepository;
import com.whatsup.whatsclone.messaging.domain.user.service.UserReader;
import com.whatsup.whatsclone.messaging.domain.user.service.UserSyncronizer;
import com.whatsup.whatsclone.messaging.domain.user.valueobject.UserEmail;
import com.whatsup.whatsclone.shared.authentication.application.AuthenticatedUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserApplicationService {

    private final UserSyncronizer userSyncronizer;
    private final UserReader userReader;

    public UserApplicationService(UserRepository userRepository) {
        this.userSyncronizer = new UserSyncronizer(userRepository);
        this.userReader = new UserReader(userRepository);
    }

    @Transactional
    public User getAuthenticatedWithSync(Jwt oAuth2User, boolean forceResync){
        userSyncronizer.syncWithId(oAuth2User, forceResync);
        return userReader.getByEmail(new UserEmail(AuthenticatedUser.username().get()))
                .orElseThrow();
    }
}
