package com.cshop.cosmeticshop.service.impl;

import com.cshop.cosmeticshop.domain.entity.Doctor;
import com.cshop.cosmeticshop.domain.entity.WeekendDay;
import com.cshop.cosmeticshop.domain.entity.WorkWeek;
import com.cshop.cosmeticshop.domain.entity.constants.Role;
import com.cshop.cosmeticshop.domain.entity.constants.Status;
import com.cshop.cosmeticshop.exception.DoctorNotFoundException;
import com.cshop.cosmeticshop.repository.DoctorRepository;
import com.cshop.cosmeticshop.service.DoctorService;
import com.cshop.cosmeticshop.service.WeekendDayService;
import com.cshop.cosmeticshop.service.WorkWeekService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Class implements DoctorService interface.
 *
 * @author Pave1Pal
 */
@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final PasswordEncoder encoder;
    private final DoctorRepository doctorRepository;
    private final WorkWeekService workWeekService;
    private final WeekendDayService weekendDayService;

    @Override
    public Doctor create(Doctor doctor) {
        doctor.setStatus(Status.ACTIVE);
        doctor.setRole(Role.DOCTOR);
        doctor.setPassword(encoder.encode(doctor.getPassword()));
        return doctorRepository.save(doctor);
    }


    @Override
    public List<Doctor> getAllDoctors(Pageable pageable) {
        return doctorRepository.findAll(pageable).getContent();
    }

    @SneakyThrows
    @Override
    public Doctor findById(Long id) {
        return doctorRepository.findById(id).orElseThrow(() -> new DoctorNotFoundException("Doctor not found " + id));
    }

    @Override
    @SneakyThrows
    @Transactional
    public WorkWeek setDoctorWorkWeek(WorkWeek workWeek, Long id) {
        Doctor doctor = findById(id);
        workWeek.setDoctor(doctor);
        return workWeekService.create(workWeek);
    }

    @Override
    @Transactional
    public WeekendDay setDoctorWeekendDay(Long doctorId, WeekendDay weekendDay) {
        Doctor doctor = findById(doctorId);
        weekendDay.setDoctor(doctor);
        return weekendDayService.create(weekendDay);
    }

}
