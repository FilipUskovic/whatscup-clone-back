package com.whatsup.whatsclone.messaging.domain.user.service;

import com.whatsup.whatsclone.messaging.domain.user.aggregate.User;
import com.whatsup.whatsclone.messaging.domain.user.repository.UserRepository;
import com.whatsup.whatsclone.messaging.domain.user.valueobject.UserEmail;

import java.util.Optional;

public class UserReader {

    private final UserRepository userRepository;

    public UserReader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getByEmail(UserEmail email) {
        return userRepository.getOneByEmail(email);
    }
}
