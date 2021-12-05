package com.cshop.cosmeticshop.repository;

import com.cshop.cosmeticshop.domain.intity.Reception;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author:Pave1Pal
 * Repository for Reception entity
 */
@Repository
public interface ReceptionRepo extends CrudRepository<Reception, Long> {
}
