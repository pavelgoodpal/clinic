package com.cshop.cosmeticshop.mapper;

import com.cshop.cosmeticshop.domain.dto.CartDto;
import com.cshop.cosmeticshop.domain.entity.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {

    CartDto CartToCartDto(Cart cart);
    Cart CartDtoToCart(CartDto cartDto);
}
