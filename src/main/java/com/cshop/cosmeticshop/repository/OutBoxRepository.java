package com.cshop.cosmeticshop.repository;

import com.cshop.cosmeticshop.domain.entity.OutBox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutBoxRepository extends JpaRepository<OutBox, Long> {
}
