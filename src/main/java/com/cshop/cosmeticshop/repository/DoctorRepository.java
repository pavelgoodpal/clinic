package com.cshop.cosmeticshop.repository;

import com.cshop.cosmeticshop.domain.entity.Doctor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Long> {
}
