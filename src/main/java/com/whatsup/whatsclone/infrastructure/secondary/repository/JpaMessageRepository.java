package com.whatsup.whatsclone.infrastructure.secondary.repository;

import com.whatsup.whatsclone.infrastructure.secondary.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMessageRepository extends JpaRepository<MessageEntity, Long> {
}
