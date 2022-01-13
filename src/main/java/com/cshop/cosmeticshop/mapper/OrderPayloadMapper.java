package com.cshop.cosmeticshop.mapper;

import com.cshop.cosmeticshop.domain.model.OrderPayload;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.validation.Payload;

public interface OrderPayloadMapper {

    String toJson(OrderPayload payload) throws JsonProcessingException;
    Payload toPayload(String json) throws JsonProcessingException;
}
