package com.cshop.cosmeticshop.security;

import com.cshop.cosmeticshop.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author:Pave1Pal
 * Class implements UserDetailsService
 */
@Service("userDetailsService")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepo userRepo;


    /**
     * Method find user by email in user repository. If user in repository return user implemented UserDetail.
     * If user not found in repository thore UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
         var user = userRepo.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User Not Found"));
        return new UserPrincipal(user);
    }


}
