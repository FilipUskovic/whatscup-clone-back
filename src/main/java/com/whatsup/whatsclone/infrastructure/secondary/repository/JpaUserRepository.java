package com.whatsup.whatsclone.infrastructure.secondary.repository;

import com.whatsup.whatsclone.infrastructure.secondary.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
}
