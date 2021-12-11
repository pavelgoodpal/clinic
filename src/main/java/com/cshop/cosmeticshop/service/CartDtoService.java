package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.dto.CartDto;
import org.springframework.stereotype.Service;

/**
 * Service for CartDto
 */
@Service
public interface CartDtoService {

    /**
     * Calculate total price of cart
     * @param cartDto CartDto
     */
    void calculateTotalPrice(CartDto cartDto);
}
