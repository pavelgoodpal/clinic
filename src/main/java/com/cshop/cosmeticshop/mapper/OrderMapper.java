package com.cshop.cosmeticshop.mapper;

import com.cshop.cosmeticshop.domain.dto.OrderDto;
import com.cshop.cosmeticshop.domain.entity.Order;
import org.mapstruct.Mapper;

/**
 * Mapper for Order and OrderDto
 * @author Pave1Pal
 */
@Mapper(componentModel = "spring")
public interface OrderMapper {

    /**
     * Convert Order into OrderDto
     * @param order Order convert into OrderDto
     * @return OrderDto
     */
    OrderDto toDto(Order order);

    /**
     * Convert OrderDto into Order
     * @param orderDto OrderDto convert into Order
     * @return OrderDto
     */
    Order fromDto(OrderDto orderDto);
}
