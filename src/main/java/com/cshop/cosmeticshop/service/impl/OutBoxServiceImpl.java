package com.cshop.cosmeticshop.service.impl;

import com.cshop.cosmeticshop.domain.entity.Order;
import com.cshop.cosmeticshop.domain.entity.OutBox;
import com.cshop.cosmeticshop.domain.model.OrderPayload;
import com.cshop.cosmeticshop.mapper.OrderPayloadMapper;
import com.cshop.cosmeticshop.repository.OutBoxRepository;
import com.cshop.cosmeticshop.service.OutBoxService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OutBoxServiceImpl implements OutBoxService {

    private final OutBoxRepository outBoxRepository;
    private final OrderPayloadMapper mapper;

    @Override
    public OutBox save(Order order) throws JsonProcessingException {
        OrderPayload payload = OrderPayload.createFromOrder(order);
        OutBox info = new OutBox();
        info.setPayload(mapper.toJson(payload));
        info.setEventType("CREATE");
        return outBoxRepository.save(info);
    }
}
