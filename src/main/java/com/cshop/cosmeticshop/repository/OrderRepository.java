package com.cshop.cosmeticshop.repository;

import com.cshop.cosmeticshop.domain.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Order entity
 * @author Pave1Pal
 */
@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}
