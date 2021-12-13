package com.cshop.cosmeticshop.configuration;

import com.cshop.cosmeticshop.service.impl.AuditorAwareImpl;
import com.cshop.cosmeticshop.domain.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Configuration for persistence
 * @author Pave1Pal
 */
@Configuration
@EnableTransactionManagement
@EnableJpaAuditing
public class PersistenceConfig {

    /**
     * Return AuditorAware as a bean
     * @return AuditorAware
     */
    @Bean
    public AuditorAware<User> auditorProvider() {
        return new AuditorAwareImpl();
    }
}
