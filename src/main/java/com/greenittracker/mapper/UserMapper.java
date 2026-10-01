package com.greenittracker.mapper;

import com.greenittracker.dto.request.RegisterRequestDto;
import com.greenittracker.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(RegisterRequestDto dto) {

        return User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .build();
    }
}