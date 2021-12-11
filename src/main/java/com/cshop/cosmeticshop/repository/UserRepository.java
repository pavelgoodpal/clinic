package com.cshop.cosmeticshop.repository;

import com.cshop.cosmeticshop.domain.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for user entity
 * @author Pave1Pal
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Find user by email address in repository
     * @param email String email adder
     * @return Optional<User>
     */
    Optional<User> findByEmail(String email);

}
