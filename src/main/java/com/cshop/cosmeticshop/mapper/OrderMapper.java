package com.cshop.cosmeticshop.mapper;

import com.cshop.cosmeticshop.domain.dto.OrderDto;
import com.cshop.cosmeticshop.domain.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDto orderToOrderDto(Order order);
    Order orderDtoToOrder(OrderDto orderDto);
}
