package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.dto.RegistrationForm;
import com.cshop.cosmeticshop.domain.intity.User;

public interface UserService {

    User findByEmail(String email);

    User save(RegistrationForm form);
}
