package com.cshop.cosmeticshop.repository;

import com.cshop.cosmeticshop.domain.entity.OutBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for OutBox entity
 * @author Pave1Pal
 */
@Repository
public interface OutBoxRepository extends JpaRepository<OutBox, Long> {

}
