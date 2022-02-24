package com.cshop.cosmeticshop.service.impl;

import com.cshop.cosmeticshop.domain.entity.Doctor;
import com.cshop.cosmeticshop.domain.entity.Order;
import com.cshop.cosmeticshop.domain.entity.WorkDay;
import com.cshop.cosmeticshop.domain.entity.WorkWeek;
import com.cshop.cosmeticshop.service.TextBuilderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Locale;

/**
 * Class implements TextBuilderService.
 *
 * @author PavelPa1
 */
@Service
public class TextBuilderServiceImpl implements TextBuilderService {

    @Value("${base.path}")
    private String basePath;
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
        String text = "";
        Collection<WorkDay> workDays = workWeek.getDaysOfWeek().values();
        return workDays.stream()
                .reduce(text, (s, workDay) -> s + makeTextFromWorkDay(workDay), String::concat) +
                "If you accept this work week schedule click on link below\n" +
                basePath +"work-weeks/activation-code/" + workWeek.getActivationCode();
    }

    /**
     * Make text from work day.
     *
     * @param workDay work day
     * @return string information containing in work day.
     */
    private String makeTextFromWorkDay(WorkDay workDay) {
        String text = makeLowCaseDayOfWeek(workDay) + "\n";
        if (workDay.isWorkDay()) {
            text += "start at: " + workDay.getWorkStartAt() + "\n";
            text += "finish at: " + workDay.getWorkFinishAt() + "\n";
        } else {
            text += "Is not work day\n";
        }
        return text;
    }

    /**
     * Make low case day of week from work day
     *
     * @param workDay day of week
     * @return string day of week
     */
    private String makeLowCaseDayOfWeek(WorkDay workDay) {
        return workDay.getDayOfWeek().toString().toLowerCase(Locale.ROOT);
    }

    @Override
    public String buildDoctorWorkWeekActivationMessage(Doctor doctor) {
        return "Hello " + doctor.getFirstName() + " your work week was accepted";
    }

    @Override
    public String buildAdminWorkWeekActivationMessage(Doctor doctor) {
        return "Doctor " + doctor.getLastName() + " accept work week";
    }
}
