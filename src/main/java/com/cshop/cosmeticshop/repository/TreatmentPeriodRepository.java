package com.cshop.cosmeticshop.repository;

import com.cshop.cosmeticshop.domain.entity.TreatmentPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreatmentPeriodRepository extends JpaRepository<TreatmentPeriod, Long> {
}
