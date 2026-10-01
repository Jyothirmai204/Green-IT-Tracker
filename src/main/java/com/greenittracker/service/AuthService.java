package com.greenittracker.service;

import com.greenittracker.dto.request.LoginRequestDto;
import com.greenittracker.dto.request.RegisterRequestDto;
import com.greenittracker.dto.response.LoginResponseDto;
import com.greenittracker.dto.response.RegisterResponseDto;

public interface AuthService {

    RegisterResponseDto register(
            RegisterRequestDto requestDto);

    LoginResponseDto login(
            LoginRequestDto requestDto);
}