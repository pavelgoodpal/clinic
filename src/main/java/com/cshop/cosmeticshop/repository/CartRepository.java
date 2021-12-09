package com.cshop.cosmeticshop.repository;

import com.cshop.cosmeticshop.domain.entity.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author:Pave1Pal
 * Repository for Cart entity
 */
@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {
}
