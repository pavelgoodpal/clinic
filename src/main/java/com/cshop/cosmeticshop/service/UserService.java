package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.dto.RegistrationFormDTO;
import com.cshop.cosmeticshop.domain.intity.User;

public interface UserService {

    User findByEmail(String email);

    User save(RegistrationFormDTO form);
}
