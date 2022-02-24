package com.cshop.cosmeticshop.mapper;

import com.cshop.cosmeticshop.domain.dto.CartDto;
import com.cshop.cosmeticshop.domain.entity.Cart;
import org.mapstruct.Mapper;

/**
 * Mapper for Cart and CartDto
 *
 * @author Pave1Pal
 */
@Mapper(componentModel = "spring")
public interface CartMapper {

    /**
     * Convert Cart into CartDto
     *
     * @param cart Cart convert into CartDto
     * @return CartDto
     */
    CartDto toDto(Cart cart);

    /**
     * Convert CartDto into Cart
     *
     * @param cartDto CartDto convert into Cart
     * @return Cart
     */
    Cart fromDto(CartDto cartDto);
}
