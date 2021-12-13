package com.cshop.cosmeticshop.service.impl;

import com.cshop.cosmeticshop.domain.entity.User;
import com.cshop.cosmeticshop.security.UserPrincipal;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * Class implements AuditorAware interface
 * @author Pave1Pal
 */
public class AuditorAwareImpl implements AuditorAware<User> {

    @Override
    public Optional<User> getCurrentAuditor() {
        var principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Optional.of(principal.getUser());
    }
}
