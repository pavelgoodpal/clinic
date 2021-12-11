package com.cshop.cosmeticshop.security;

import com.cshop.cosmeticshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Class implements UserDetailsService
 * @author Pave1Pal
 */
@Primary
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;


    /**
     * Find user by email in repository and return his permissions.
     * @param email String email address of user
     * @return UserDetails - permissions and roles of user
     * @throws UsernameNotFoundException If user not found
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
         var user = userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User Not Found"));
        return new UserPrincipal(user);
    }


}
