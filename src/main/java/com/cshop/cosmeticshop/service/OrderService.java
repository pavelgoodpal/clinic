package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.intity.Cart;
import com.cshop.cosmeticshop.domain.intity.Order;
import com.cshop.cosmeticshop.domain.intity.User;

public interface OrderService {

    Order saveOrder(Order order, Cart cart, User user);
}
