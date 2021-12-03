package com.cshop.cosmeticshop.repository;

import com.cshop.cosmeticshop.domain.intity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends CrudRepository<Order, Long> {
}
