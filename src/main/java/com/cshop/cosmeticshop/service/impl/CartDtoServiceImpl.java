package com.cshop.cosmeticshop.service.impl;

import com.cshop.cosmeticshop.domain.dto.CartDto;
import com.cshop.cosmeticshop.domain.entity.Treatment;
import com.cshop.cosmeticshop.service.CartDtoService;
import org.springframework.stereotype.Service;

@Service
public class CartDtoServiceImpl implements CartDtoService {

    @Override
    public void calculateTotalPrice(CartDto cartDto) {
        long price = 0L;
        for (Treatment treatment : cartDto.getTreatments()) {
            price += treatment.getPrice();
        }
        cartDto.setTotalPrice(price);
    }

}
