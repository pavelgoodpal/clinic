package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.intity.Order;
import com.cshop.cosmeticshop.domain.intity.User;

public interface EmailService {

    void sendMessage(Order order, User user);
}
