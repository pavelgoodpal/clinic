package com.cshop.cosmeticshop.configuration;

import com.cshop.cosmeticshop.service.CurrentUserService;
import com.cshop.cosmeticshop.domain.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;

/**
 * Configuration for persistence
 * @author Pave1Pal
 */
@Configuration
@EnableTransactionManagement
@EnableJpaAuditing
@EnableAspectJAutoProxy
public class PersistenceConfig {

    /**
     * Return AuditorAware as a bean
     * @return AuditorAware
     */
    @Bean
    public AuditorAware<User> auditorProvider(CurrentUserService userService) {
        return () -> Optional.ofNullable(userService.getUser());
    }
}
