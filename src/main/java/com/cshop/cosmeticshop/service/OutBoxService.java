package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.entity.Order;
import com.cshop.cosmeticshop.domain.entity.OutBox;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

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
    OutBox buildEmail(Order order);

    /**
     * Find all outbox info in outbox data storage
     *
     * @return List of outbox info
     */
    List<OutBox> findAll();

    /**
     * Delete outbox info from outbox data storage
     *
     * @param outBox info
     */
    void delete(OutBox outBox);
}
