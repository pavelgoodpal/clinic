package com.cshop.cosmeticshop.mapper;

import com.cshop.cosmeticshop.domain.dto.OrderDto;
import com.cshop.cosmeticshop.domain.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDto orderToOrderDto(Order order);
    Order orderDtoToOrder(OrderDto orderDto);
}
