package com.cshop.cosmeticshop.mapper;

import com.cshop.cosmeticshop.domain.model.OrderPayload;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.validation.Payload;

/**
 * Mapper for OrderPayload and Json string
 * @
 */
public interface OrderPayloadMapper {

    /**
     * Map OrderPayload to json
     * @param payload needed information about order
     * @return order payload json
     * @throws JsonProcessingException if it can be
     */
    String toJson(OrderPayload payload) throws JsonProcessingException;

    /**
     * Map json to OrderPayload
     * @param json with OrderPayload info
     * @return OrderPayload
     * @throws JsonProcessingException if it can be
     */
    Payload toPayload(String json) throws JsonProcessingException;
}
