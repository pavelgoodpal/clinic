package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.entity.Doctor;
import com.cshop.cosmeticshop.domain.entity.WeekendDay;
import com.cshop.cosmeticshop.domain.entity.WorkWeek;
import com.cshop.cosmeticshop.exception.DoctorNotFoundException;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for Doctors.
 *
 * @author Pave1Pal
 */
public interface DoctorService {

    /**
     * Create new Doctor.
     *
     * @param doctor who will be created
     * @return created Doctor
     */
    Doctor create(Doctor doctor);

    /**
     * Find all doctors using pageable interface.
     *
     * @param pageable page parameters
     * @return list of doctors
     */
    List<Doctor> getAllDoctors(Pageable pageable);

    /**
     * Find doctor by id.
     *
     * @param id of doctor
     * @return found Doctor
     */
    Doctor findById(Long id);

    /**
     * Set workWeek to doctor using doctor id.
     *
     * @param workWeek of doctor
     * @param id of doctor
     * @return set workWeek
     */
    WorkWeek setDoctorWorkWeek(WorkWeek workWeek, Long id);

    /**
     * Set weekend day to doctor.
     *
     * @param doctorId doctor id
     * @param weekendDay doctor weekend day for setting
     * @return set weekend day
     */
    WeekendDay setDoctorWeekendDay(Long doctorId, WeekendDay weekendDay);

}
