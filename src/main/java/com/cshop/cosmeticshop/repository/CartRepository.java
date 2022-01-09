package com.cshop.cosmeticshop.repository;

import com.cshop.cosmeticshop.domain.entity.BaseEntity;
import com.cshop.cosmeticshop.domain.entity.Cart;
import com.cshop.cosmeticshop.domain.entity.User;
import com.cshop.cosmeticshop.domain.entity.constants.CartStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for Cart entity
 * @author Pave1Pal
 */
@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {

    /**
     * Find cart using user and status of cart
     * @param user from UserPrincipal
     * @param status status of cart
     * @return list of carts
     */
    Optional<Cart> findCartByUserAndStatus(User user, CartStatus status);
}