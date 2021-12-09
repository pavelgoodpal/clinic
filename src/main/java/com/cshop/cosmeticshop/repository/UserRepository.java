package com.cshop.cosmeticshop.repository;

import com.cshop.cosmeticshop.domain.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author:Pave1Pal
 * Repository for user entity
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * method find user by email address in repository
     */
    Optional<User> findByEmail(String email);

}
