package com.cshop.cosmeticshop.mapper.impl;

import com.cshop.cosmeticshop.domain.dto.PayloadDTO;
import com.cshop.cosmeticshop.mapper.PayloadJsonMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.Payload;

@Component
@RequiredArgsConstructor
public class PayloadJsonMapperImpl implements PayloadJsonMapper {

    private final ObjectMapper mapper;

    @Override
    public String toJson(PayloadDTO payload) throws JsonProcessingException {
        return mapper.writeValueAsString(payload);
    }

    @Override
    public Payload toPayload(String json) throws JsonProcessingException {
        return mapper.readValue(json, Payload.class);
    }
}
