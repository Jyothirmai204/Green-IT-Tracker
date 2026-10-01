package com.greenittracker.controller;

import com.greenittracker.dto.request.LoginRequestDto;
import com.greenittracker.dto.request.RegisterRequestDto;
import com.greenittracker.dto.response.ApiResponseDto;
import com.greenittracker.dto.response.LoginResponseDto;
import com.greenittracker.dto.response.RegisterResponseDto;
import com.greenittracker.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<ApiResponseDto<RegisterResponseDto>> register(
            @Valid @RequestBody RegisterRequestDto requestDto) {

        RegisterResponseDto response =
                authService.register(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponseDto.<RegisterResponseDto>builder()
                                .success(true)
                                .message("User registered successfully")
                                .status(HttpStatus.CREATED.value())
                                .timestamp(LocalDateTime.now())
                                .data(response)
                                .build()
                );
    }


    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto<LoginResponseDto>> login(
            @Valid @RequestBody LoginRequestDto requestDto) {

        LoginResponseDto response =
                authService.login(requestDto);

        return ResponseEntity.ok(
                ApiResponseDto.<LoginResponseDto>builder()
                        .success(true)
                        .message("Login successful")
                        .status(HttpStatus.OK.value())
                        .timestamp(LocalDateTime.now())
                        .data(response)
                        .build()
        );
    }
}