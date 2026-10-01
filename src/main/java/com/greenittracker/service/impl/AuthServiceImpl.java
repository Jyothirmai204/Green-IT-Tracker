package com.greenittracker.service.impl;

import com.greenittracker.dto.request.LoginRequestDto;
import com.greenittracker.dto.request.RegisterRequestDto;
import com.greenittracker.dto.response.LoginResponseDto;
import com.greenittracker.dto.response.RegisterResponseDto;
import com.greenittracker.entity.Role;
import com.greenittracker.entity.User;
import com.greenittracker.exception.DuplicateUserException;
import com.greenittracker.exception.UserNotFoundException;
import com.greenittracker.mapper.UserMapper;
import com.greenittracker.repository.RoleRepository;
import com.greenittracker.repository.UserRepository;
import com.greenittracker.security.JwtUtil;
import com.greenittracker.service.AuditLogService;
import com.greenittracker.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final AuditLogService auditLogService;

    @Override
    public RegisterResponseDto register(
            RegisterRequestDto requestDto) {

        log.info("Registering user with email: {}", requestDto.getEmail());

        if (userRepository.existsByEmail(requestDto.getEmail())) {

            throw new DuplicateUserException(
                    "Email already registered"
            );
        }

        User user = userMapper.toEntity(requestDto);

        user.setActive(true);

        user.setPassword(
                passwordEncoder.encode(
                        requestDto.getPassword()
                )
        );

        Role role = roleRepository.findByRoleName("ROLE_USER")
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "Default role not found"
                        )
                );

        user.setRoles(Set.of(role));

        User savedUser = userRepository.save(user);

        auditLogService.saveAuditLog(
                savedUser.getEmail(),
                "REGISTER",
                "AUTH",
                "User Registration Successful"
        );

        return RegisterResponseDto.builder()
                .userId(savedUser.getId())
                .fullName(
                        savedUser.getFirstName()
                                + " "
                                + savedUser.getLastName()
                )
                .email(savedUser.getEmail())
                .build();
    }

    @Override
    public LoginResponseDto login(
            LoginRequestDto requestDto) {

        log.info("Login attempt for email: {}", requestDto.getEmail());

        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.getEmail(),
                            requestDto.getPassword()
                    )
            );

        } catch (BadCredentialsException ex) {

            throw new BadCredentialsException(
                    "Invalid Credentials"
            );
        }

        User user = userRepository
                .findByEmail(requestDto.getEmail())
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found"
                        )
                );

        String token =
                jwtUtil.generateToken(
                        user.getEmail()
                );

        auditLogService.saveAuditLog(
                user.getEmail(),
                "LOGIN",
                "AUTH",
                "User Login Successful"
        );

        return LoginResponseDto.builder()
                .userId(user.getId())
                .fullName(
                        user.getFirstName()
                                + " "
                                + user.getLastName()
                )
                .email(user.getEmail())
                .token(token)
                .build();
    }
}