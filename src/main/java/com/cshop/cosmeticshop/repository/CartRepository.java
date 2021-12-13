package com.cshop.cosmeticshop.repository;

import com.cshop.cosmeticshop.domain.entity.Cart;
import com.cshop.cosmeticshop.domain.entity.User;
import com.cshop.cosmeticshop.domain.entity.constants.CartStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Cart entity
 * @author Pave1Pal
 */
@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {

    /**
     * Find carts using user info and status of it
     * @param user from UserPrincipal
     * @param status status of cart
     * @return list of carts depends on status
     */
    List<Cart> findCartByUserAndStatusOrderByCreationTimeDesc(User user, CartStatus status);
}
