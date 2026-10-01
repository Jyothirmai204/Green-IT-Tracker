package com.greenittracker.security;

import com.greenittracker.entity.User;
import com.greenittracker.exception.UserNotFoundException;
import com.greenittracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService
        implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(
            String email)
            throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with email : "
                                        + email
                        ));

        return new org.springframework.security.core.userdetails
                .User(
                user.getEmail(),
                user.getPassword(),
                user.getRoles()
                        .stream()
                        .map(role ->
                                new SimpleGrantedAuthority(
                                        role.getRoleName()
                                )
                        )
                        .collect(Collectors.toList())
        );
    }
}