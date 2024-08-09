package com.whatsup.whatsclone.wire.postgresql.infrastructure;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
@EnableMethodSecurity
@EnableJpaRepositories(basePackages = {"com.whatsup.whatsclone"})
@EnableJpaAuditing
public class DataBaseConfiguration {
}
