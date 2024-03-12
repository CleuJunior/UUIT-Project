package com.interion.uuit.services;

import com.interion.uuit.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger LOG = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = (UserDetails) this.repository
                .findByEmail(username)
                .orElseThrow(() -> {
                    LOG.warn("User {} not found", username);
                    return new UsernameNotFoundException("User not found!");
                });

        return new User(
                user.getUsername(),
                user.getPassword(),
                user.getAuthorities()
        );
    }
}
