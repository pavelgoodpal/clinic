package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.entity.OutBox;
import com.cshop.cosmeticshop.domain.model.OrderPayload;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface OutBoxService {

    OutBox create(OrderPayload payload) throws JsonProcessingException;
}
