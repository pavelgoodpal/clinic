package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.dto.CartDto;

public interface CartDtoService {
    void calculateTotalPrice(CartDto cartDto);
}
