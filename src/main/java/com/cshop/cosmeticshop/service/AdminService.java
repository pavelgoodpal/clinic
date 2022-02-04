package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.entity.User;

import java.util.List;

public interface AdminService {

    List<User> getAdmins();
}
