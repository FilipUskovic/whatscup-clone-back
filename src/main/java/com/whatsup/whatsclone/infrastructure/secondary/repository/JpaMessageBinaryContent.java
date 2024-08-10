package com.whatsup.whatsclone.infrastructure.secondary.repository;

import com.whatsup.whatsclone.infrastructure.secondary.entity.MessageContentBinaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMessageBinaryContent extends JpaRepository<MessageContentBinaryEntity, Long> {
}
