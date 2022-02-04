package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.entity.Order;
import com.cshop.cosmeticshop.domain.entity.OutBox;
import com.cshop.cosmeticshop.domain.entity.WorkWeek;
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
    OutBox buildOrderEmail(Order order);

    /**
     * Save work week information in outbox table
     *
     * @param workWeek of doctor
     * @return outbox data from work week
     */
    OutBox buildCreateWorkWeekEmail(WorkWeek workWeek);

    /**
     * Build and save outbox info about work week.
     *
     * @param workWeek for doctor
     * @return outBox info which will be sent to doctor
     */
    OutBox buildUpdateWorkWeekEmailForDoctor(WorkWeek workWeek);

    /**
     * Build and save outbox info about doctor work week for admins
     *
     * @param workWeek for doctor
     * @return  List of outBox info which will be sent to admins
     */
    List<OutBox> buildUpdateWorkWeekEmailForAdmins(WorkWeek workWeek);

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
