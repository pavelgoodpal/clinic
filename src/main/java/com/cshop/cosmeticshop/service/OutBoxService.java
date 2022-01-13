package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.entity.Order;
import com.cshop.cosmeticshop.domain.entity.OutBox;
import com.cshop.cosmeticshop.domain.model.OrderPayload;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface OutBoxService {

    OutBox save(Order order) throws JsonProcessingException;
}
