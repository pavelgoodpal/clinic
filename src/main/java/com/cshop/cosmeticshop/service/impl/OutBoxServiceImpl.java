package com.cshop.cosmeticshop.service.impl;

import com.cshop.cosmeticshop.domain.entity.Order;
import com.cshop.cosmeticshop.domain.entity.OutBox;
import com.cshop.cosmeticshop.domain.dto.PayloadDTO;
import com.cshop.cosmeticshop.domain.entity.constants.EventType;
import com.cshop.cosmeticshop.mapper.PayloadJsonMapper;
import com.cshop.cosmeticshop.repository.OutBoxRepository;
import com.cshop.cosmeticshop.service.OutBoxService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OutBoxServiceImpl implements OutBoxService {

    private final OutBoxRepository outBoxRepository;

    @Override
    public OutBox save(Order order) {
        String payload = createPayloadFromOrder(order);
        OutBox outBox = new OutBox();
        outBox.setPayload(payload);
        outBox.setDestination(order.getEmail());
        outBox.setEventType(EventType.CREATE);
        return outBoxRepository.save(outBox);
    }

    @Override
    public List<OutBox> findAll() {
        return  outBoxRepository.findAll();
    }

    @Override
    public void delete(OutBox outBox) {
        outBoxRepository.delete(outBox);
    }

    private String createPayloadFromOrder(Order order) {
        String date = order.getStartAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
        return "We very happy that you use our service!!!\n" +
                order.getUser().getFirstName() + ", You have got an appointment to " + date + ".\n" +
                "Please do not late and have a nice day!";
    }

}
