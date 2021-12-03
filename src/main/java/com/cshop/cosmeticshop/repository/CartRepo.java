package com.cshop.cosmeticshop.repository;

import com.cshop.cosmeticshop.domain.intity.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends CrudRepository<Cart, Long> {
}
