package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.entity.Order;
import com.cshop.cosmeticshop.domain.entity.OutBox;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Service to control outbox info
 * @author Pave1Pal
 */
public interface OutBoxService {

    /**
     * Save order information in outbox table
     * @param order info
     * @return OutBox - information in table
     * @throws JsonProcessingException if it take place
     */
    OutBox save(Order order) throws JsonProcessingException;
}
