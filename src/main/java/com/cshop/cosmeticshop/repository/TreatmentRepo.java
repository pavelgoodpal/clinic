package com.cshop.cosmeticshop.repository;

import com.cshop.cosmeticshop.domain.intity.Treatment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreatmentRepo extends CrudRepository<Treatment, Long> {
}
