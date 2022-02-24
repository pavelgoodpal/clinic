package com.cshop.cosmeticshop.service.impl;

import com.cshop.cosmeticshop.domain.entity.*;
import com.cshop.cosmeticshop.domain.entity.constants.EventType;
import com.cshop.cosmeticshop.repository.OutBoxRepository;
import com.cshop.cosmeticshop.service.AdminService;
import com.cshop.cosmeticshop.service.OutBoxService;
import com.cshop.cosmeticshop.service.TextBuilderService;
import com.cshop.cosmeticshop.service.WorkWeekService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OutBoxServiceImpl implements OutBoxService {

    private final OutBoxRepository outBoxRepository;
    private final AdminService adminService;
    private final TextBuilderService textBuilder;
    private final WorkWeekService workWeekService;

    @Override
    @Transactional
    public OutBox buildOrderEmail(Order order) {
        String payload = textBuilder.buildEmailFromOrder(order);
        OutBox outBox = new OutBox();
        outBox.setPayload(payload);
        outBox.setDestination(order.getEmail());
        outBox.setEventType(EventType.CREATE_ORDER_EMAIL);
        return outBoxRepository.save(outBox);
    }

    @Override
    @Transactional
    public OutBox buildCreateWorkWeekEmail(WorkWeek workWeek) {
        String payload = textBuilder.buildWorkWeekEmailForDoctor(workWeek);
        OutBox outBox = new OutBox();
        outBox.setPayload(payload);
        outBox.setDestination(workWeek.getDoctor().getEmail());
        outBox.setEventType(EventType.SET_DOCTOR_WORK_WEEK);
        return outBoxRepository.save(outBox);
    }

    @Override
    @Transactional
    public OutBox buildUpdateWorkWeekEmailForDoctor(WorkWeek workWeek) {
        OutBox outBox = new OutBox();
        outBox.setEventType(EventType.UPDATE_DOCTOR_WORK_WEEK);
        outBox.setPayload(textBuilder.buildWorkWeekEmailForDoctor(workWeek));
        outBox.setDestination(workWeek.getDoctor().getEmail());
        return outBoxRepository.save(outBox);
    }

    @Override
    @Transactional
    public List<OutBox> buildUpdateWorkWeekEmailForAdmins(WorkWeek workWeek) {
        return adminService.getAllAdmins().stream()
                .map(admin -> buildWorkWeekOutBoxForOneAdmin(admin, workWeek))
                .collect(Collectors.toList());
    }

    /**
     * Build and save outbox info about doctor work week for admin
     *
     * @param admin    who receive email
     * @param workWeek doctor
     * @return saved OutBox object
     */
    private OutBox buildWorkWeekOutBoxForOneAdmin(User admin, WorkWeek workWeek) {
        OutBox outBox = new OutBox();
        outBox.setPayload(textBuilder.buildWorkWeekEmailForAdmin(workWeek));
        outBox.setDestination(admin.getEmail());
        outBox.setEventType(EventType.UPDATE_DOCTOR_WORK_WEEK);
        return outBoxRepository.save(outBox);
    }

    @Override
    @Transactional
    public List<OutBox> findAll() {
        Page<OutBox> page = outBoxRepository.findAll(PageRequest.of(0, 25));
        List<OutBox> outBoxList = page.getContent();
        int pageNumber = page.getTotalPages();
        addResiduaryContent(pageNumber, outBoxList);
        return outBoxList;
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

    @Override
    @Transactional
    public void delete(OutBox outBox) {
        outBoxRepository.delete(outBox);
    }

    @Override
    @Transactional
    public OutBox buildActivateWorkWeekEmailForDoctor(UUID activationCode) {
        WorkWeek workWeek = workWeekService.getByActivationCode(activationCode);
        Doctor doctor = workWeek.getDoctor();
        OutBox outBox = new OutBox();
        outBox.setEventType(EventType.ACCEPT_DOCTOR_WORK_WEEK);
        outBox.setDestination(doctor.getEmail());
        outBox.setPayload(textBuilder.buildDoctorWorkWeekActivationMessage(doctor));
        return outBoxRepository.save(outBox);
    }

    @Override
    @Transactional
    public List<OutBox> buildActivateWorkWeekEmailForAdmins(UUID activationCode) {
        WorkWeek workWeek = workWeekService.getByActivationCode(activationCode);
        Doctor doctor = workWeek.getDoctor();
        return adminService.getAllAdmins().stream()
                .map(admin -> buildActivateWorkWeekEmailForOneAdmin(doctor, admin))
                .collect(Collectors.toList());
    }

    /**
     * Build activate work week email message for one admin.
     *
     * @param doctor doctor of work week
     * @param admin  admin who receive the message
     * @return OutBox
     */
    private OutBox buildActivateWorkWeekEmailForOneAdmin(Doctor doctor, User admin) {
        OutBox outBox = new OutBox();
        outBox.setDestination(admin.getEmail());
        outBox.setEventType(EventType.ACCEPT_DOCTOR_WORK_WEEK);
        outBox.setPayload(textBuilder.buildAdminWorkWeekActivationMessage(doctor));
        return outBoxRepository.save(outBox);
    }
}
