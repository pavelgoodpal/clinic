package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.entity.Doctor;
import com.cshop.cosmeticshop.domain.entity.Order;
import com.cshop.cosmeticshop.domain.entity.WorkWeek;

/**
 * Service builds text from objects.
 *
 * @author PavelPa1
 */
public interface TextBuilderService {

    /**
     * Create from order payload
     *
     * @param order info
     * @return payload
     */
    String buildEmailFromOrder(Order order);

    /**
     * Build Email payload text from work week of doctor.
     *
     * @param workWeek doctor work week
     * @return information are converted to String
     */
    String buildWorkWeekEmailForDoctor(WorkWeek workWeek);

    /**
     * Build text for email sending for admin
     *
     * @param workWeek doctor work week
     * @return text for admin about doctor work week
     */
    String buildWorkWeekEmailForAdmin(WorkWeek workWeek);

    /**
     * Build message for doctor that his work week was activated by admin
     *
     * @param doctor doctor
     * @return String text message for doctor that his work week was activated by admin
     */
    String buildDoctorWorkWeekActivationMessage(Doctor doctor);

    /**
     * Build message for admin that doctor accepted and activate work week
     *
     * @param doctor doctor
     * @return text that doctor accepted and activate work week for admin
     */
    String buildAdminWorkWeekActivationMessage(Doctor doctor);
}
