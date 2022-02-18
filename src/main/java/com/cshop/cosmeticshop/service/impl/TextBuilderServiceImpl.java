package com.cshop.cosmeticshop.service.impl;

import com.cshop.cosmeticshop.domain.entity.Doctor;
import com.cshop.cosmeticshop.domain.entity.Order;
import com.cshop.cosmeticshop.domain.entity.WorkWeek;
import com.cshop.cosmeticshop.service.TextBuilderService;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

/**
 * Class implements TextBuilderService.
 *
 * @author PavelPa1
 */
@Service
public class TextBuilderServiceImpl implements TextBuilderService {

    private final static DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");

    @Override
    public String buildEmailFromOrder(Order order) {
        String date = order.getStartAt().format(timeFormat);
        return "We very happy that you use our service!!!\n" +
                order.getUser().getFirstName() + ", You have got an appointment to " + date + ".\n" +
                "Please do not late and have a nice day!";
    }

    @Override
    public String buildWorkWeekEmailForDoctor(WorkWeek workWeek) {
        return "Hello " + workWeek.getDoctor().getFirstName() + "!\n" +
                "Your work week is presented below.\n" +
                convertWorkWeekToText(workWeek);
    }

    @Override
    public String buildWorkWeekEmailForAdmin(WorkWeek workWeek) {
        Doctor doctor = workWeek.getDoctor();
        return "Doctor " + doctor.getFirstName() + " " + doctor.getLastName() + " change work week schedule\n" +
                convertWorkWeekToText(workWeek);
    }

    /**
     * Make from WorkWeek object String containing work week info
     *
     * @param workWeek work week for doctor
     * @return String with work week schedule
     */
    private String convertWorkWeekToText(WorkWeek workWeek) {
        return "Monday    : Start  - " + workWeek.getMondayStart() + "\n" +
                "            Finish - " + workWeek.getMondayFinish() + "\n" +

                "Tuesday   : Start  - " + workWeek.getTuesdayStart() + "\n" +
                "            Finish - " + workWeek.getTuesdayFinish() + "\n" +

                "Wednesday : Start  - " + workWeek.getWednesdayStart() + "\n" +
                "            Finish - " + workWeek.getWednesdayFinish() + "\n" +

                "Thursday  : Start  - " + workWeek.getThursdayStart() + "\n" +
                "            Finish - " + workWeek.getThursdayFinish() + "\n" +

                "Friday    : Start  - " + workWeek.getFridayStart() + "\n" +
                "            Finish - " + workWeek.getFridayFinish() + "\n" +

                "Saturday  : Start  - " + workWeek.getSaturdayStart() + "\n" +
                "            Finish - " + workWeek.getSaturdayFinish() + "\n" +

                "Sunday    : Start  - " + workWeek.getSundayStart() + "\n" +
                "            Finish - " + workWeek.getSundayFinish() + "\n\n" +
                "If you accept this work week schedule click on link below\n" +
                "http://localhost:8080/work-weeks/activation-code/" + workWeek.getActivationCode();
    }
} 
