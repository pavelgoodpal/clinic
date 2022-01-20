package com.cshop.cosmeticshop.repository;

import com.cshop.cosmeticshop.domain.entity.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Long> {
}
