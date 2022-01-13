package com.cshop.cosmeticshop.mapper.impl;

import com.cshop.cosmeticshop.domain.model.OrderPayload;
import com.cshop.cosmeticshop.mapper.OrderPayloadMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.Payload;

@Component
@RequiredArgsConstructor
public class OrderPayloadMapperImpl implements OrderPayloadMapper {

    private final ObjectMapper mapper;

    @Override
    public String toJson(OrderPayload payload) throws JsonProcessingException {
        return mapper.writeValueAsString(payload);
    }

    @Override
    public Payload toPayload(String json) throws JsonProcessingException {
        return mapper.readValue(json, Payload.class);
    }
}
