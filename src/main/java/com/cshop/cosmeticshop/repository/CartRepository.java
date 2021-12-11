package com.cshop.cosmeticshop.repository;

import com.cshop.cosmeticshop.domain.entity.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Cart entity
 * @author Pave1Pal
 */
@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {
}
