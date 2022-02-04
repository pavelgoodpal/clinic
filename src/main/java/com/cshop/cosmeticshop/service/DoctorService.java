package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.entity.Doctor;
import com.cshop.cosmeticshop.domain.entity.WorkWeek;
import com.cshop.cosmeticshop.exception.DoctorNotFoundException;
import lombok.SneakyThrows;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DoctorService {

    Doctor create(Doctor doctor);

    List<Doctor> getAllDoctors(Pageable pageable);

    @SneakyThrows
    Doctor findById(Long id);

    WorkWeek setWorkWeekToDoctor(WorkWeek workWeek, Long id);
}
