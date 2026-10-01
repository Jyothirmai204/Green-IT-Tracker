package com.greenittracker.util;

import com.greenittracker.dto.response.ApiResponseDto;

import java.time.LocalDateTime;

public class ApiResponseUtil {

    private ApiResponseUtil() {
    }

    public static <T> ApiResponseDto<T> success(
            String message,
            int status,
            T data) {

        return ApiResponseDto
                .<T>builder()
                .success(true)
                .message(message)
                .status(status)
                .timestamp(LocalDateTime.now())
                .data(data)
                .build();
    }
}