package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.entity.Order;
import com.cshop.cosmeticshop.domain.entity.OutBox;
import com.cshop.cosmeticshop.domain.entity.WorkWeek;

import java.util.List;
import java.util.UUID;

/**
 * Service to control outbox info.
 *
 * @author Pave1Pal
 */
public interface OutBoxService {

    /**
     * Save order information in outbox table
     *
     * @param order info
     * @return OutBox - information in table
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
     * @return List of outBox info which will be sent to admins
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

    /**
     * Build and save in outbox table activation work week email for doctor.
     *
     * @param activationCode UUID activation code
     * @return saved OutBox
     */
    OutBox buildActivateWorkWeekEmailForDoctor(UUID activationCode);

    /**
     * Build and save in outbox table activation work week email for admins.
     *
     * @param activationCode code for activate work week
     * @return list of saved outboxes
     */
    List<OutBox> buildActivateWorkWeekEmailForAdmins(UUID activationCode);
}
