package com.cshop.cosmeticshop.repository;

import com.cshop.cosmeticshop.domain.entity.Reception;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Reception entity
 * @author Pave1Pal
 */
@Repository
public interface ReceptionRepository extends CrudRepository<Reception, Long> {
}
