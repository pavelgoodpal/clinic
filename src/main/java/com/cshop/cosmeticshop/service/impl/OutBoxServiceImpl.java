package com.cshop.cosmeticshop.service.impl;

import com.cshop.cosmeticshop.domain.entity.*;
import com.cshop.cosmeticshop.domain.entity.constants.EventType;
import com.cshop.cosmeticshop.repository.OutBoxRepository;
import com.cshop.cosmeticshop.service.AdminService;
import com.cshop.cosmeticshop.service.OutBoxService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OutBoxServiceImpl implements OutBoxService {

    private final OutBoxRepository outBoxRepository;
    private final AdminService adminService;

    private final static DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");

    @Override
    public OutBox buildOrderEmail(Order order) {
        String payload = buildEmailFromOrder(order);
        OutBox outBox = new OutBox();
        outBox.setPayload(payload);
        outBox.setDestination(order.getEmail());
        outBox.setEventType(EventType.CREATE_ORDER_EMAIL);
        return outBoxRepository.save(outBox);
    }

    @Override
    public OutBox buildCreateWorkWeekEmail(WorkWeek workWeek) {
        String payload = buildWorkWeekEmailForDoctor(workWeek);
        OutBox outBox = new OutBox();
        outBox.setPayload(payload);
        outBox.setDestination(workWeek.getDoctor().getEmail());
        outBox.setEventType(EventType.SET_DOCTOR_WORK_WEEK);
        return outBoxRepository.save(outBox);
    }

    @Override
    public OutBox buildUpdateWorkWeekEmailForDoctor(WorkWeek workWeek) {
        OutBox outBox = new OutBox();
        outBox.setEventType(EventType.UPDATE_DOCTOR_WORK_WEEK);
        outBox.setPayload(buildWorkWeekEmailForDoctor(workWeek));
        outBox.setDestination(workWeek.getDoctor().getEmail());
        return outBoxRepository.save(outBox);
    }

    @Override
    public List<OutBox> buildUpdateWorkWeekEmailForAdmins(WorkWeek workWeek) {
        return adminService.getAllAdmins().stream()
                .map(admin -> buildWorkWeekOutBoxForAdmin(admin, workWeek))
                .collect(Collectors.toList());
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

    /**
     * Build Email payload text from work week of doctor.
     *
     * @param workWeek doctor work week
     * @return information are converted to String
     */
    private String buildWorkWeekEmailForDoctor(WorkWeek workWeek) {
        return "Hello " + workWeek.getDoctor().getFirstName() + "!\n" +
                "Your work week is presented below.\n" +
                convertWorkWeekToText(workWeek);
    }

    /**
     * Build text for email sending for admin
     *
     * @param workWeek doctor work week
     * @return text for admin about doctor work week
     */
    private String buildWorkWeekEmailForAdmin(WorkWeek workWeek) {
        Doctor doctor = workWeek.getDoctor();
        return "Doctor " + doctor.getFirstName() + " " + doctor.getLastName() + " change work week schedule\n" +
                convertWorkWeekToText(workWeek);
    }

    /**
     * Build and save outbox info about doctor work week for admin
     *
     * @param admin who receive email
     * @param workWeek doctor
     * @return saved OutBox object
     */
    private OutBox buildWorkWeekOutBoxForAdmin(User admin, WorkWeek workWeek) {
        OutBox outBox = new OutBox();
        outBox.setPayload(buildWorkWeekEmailForAdmin(workWeek));
        outBox.setDestination(admin.getEmail());
        outBox.setEventType(EventType.UPDATE_DOCTOR_WORK_WEEK);
        return outBoxRepository.save(outBox);
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
