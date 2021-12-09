package com.cshop.cosmeticshop.repository;

import com.cshop.cosmeticshop.domain.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author:Pave1Pal
 * Repository for Order entity
 */
@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}
