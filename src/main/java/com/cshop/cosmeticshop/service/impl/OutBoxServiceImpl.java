package com.cshop.cosmeticshop.service.impl;

import com.cshop.cosmeticshop.domain.entity.Order;
import com.cshop.cosmeticshop.domain.entity.OutBox;
import com.cshop.cosmeticshop.domain.entity.constants.EventType;
import com.cshop.cosmeticshop.repository.OutBoxRepository;
import com.cshop.cosmeticshop.service.OutBoxService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OutBoxServiceImpl implements OutBoxService {

    private final OutBoxRepository outBoxRepository;
    private final static DateTimeFormatter timeFormat =  DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");

    @Override
    public OutBox buildEmail(Order order) {
        String payload = buildEmailFromOrder(order);
        OutBox outBox = new OutBox();
        outBox.setPayload(payload);
        outBox.setDestination(order.getEmail());
        outBox.setEventType(EventType.CREATE_ORDER_EMAIL);
        return outBoxRepository.save(outBox);
    }

    @Override
    public List<OutBox> findAll() {
         Page<OutBox> page = outBoxRepository.findAll(PageRequest.of(0, 25));
         List<OutBox> outBoxList = page.getContent();
         int pageNumber = page.getTotalPages();
         addResiduaryContent(pageNumber, outBoxList);
         return outBoxList;
    }

    @Override
    public void delete(OutBox outBox) {
        outBoxRepository.delete(outBox);
    }

    /**
     * Create from order payload
     *
     * @param order info
     * @return payload
     */
    private String buildEmailFromOrder(Order order) {
        String date = order.getStartAt().format(timeFormat);
        return "We very happy that you use our service!!!\n" +
                order.getUser().getFirstName() + ", You have got an appointment to " + date + ".\n" +
                "Please do not late and have a nice day!";
    }

    /**
     * Add to outbox list from first page content from other, residuary pages
     *
     * @param pageNumber number of residuary pages
     * @param outBoxList added list
     */
    private void addResiduaryContent(int pageNumber, List<OutBox> outBoxList) {
        if (pageNumber > 1) {
            for (int i = 1; i <= pageNumber; i++) {
                outBoxList.addAll((outBoxRepository.findAll(PageRequest.of(i, 25)).getContent()));
            }
        }
    }

}
