package com.cshop.cosmeticshop.service.impl;

import com.cshop.cosmeticshop.domain.entity.Order;
import com.cshop.cosmeticshop.domain.entity.OutBox;
import com.cshop.cosmeticshop.domain.dto.PayloadDTO;
import com.cshop.cosmeticshop.mapper.PayloadJsonMapper;
import com.cshop.cosmeticshop.repository.OutBoxRepository;
import com.cshop.cosmeticshop.service.OutBoxService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OutBoxServiceImpl implements OutBoxService {

    private final OutBoxRepository outBoxRepository;
    private final PayloadJsonMapper mapper;

    @Override
    public OutBox save(Order order) throws JsonProcessingException {
        PayloadDTO payload = PayloadDTO.createFromOrder(order);
        OutBox info = new OutBox();
        info.setPayload(mapper.toJson(payload));
        info.setEventType("CREATE");
        return outBoxRepository.save(info);
    }
}
