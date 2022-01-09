package com.cshop.cosmeticshop.service.impl;

import com.cshop.cosmeticshop.domain.entity.User;
import com.cshop.cosmeticshop.security.UserPrincipal;
import com.cshop.cosmeticshop.service.CurrentUserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserServiceImpl implements CurrentUserService {

    @Override
    public User getUser() {
        var principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getUser();
    }
}
