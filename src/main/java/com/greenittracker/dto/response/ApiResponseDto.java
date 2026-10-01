package com.greenittracker.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponseDto<T> {

    private boolean success;

    private String message;

    private int status;

    private LocalDateTime timestamp;

    private T data;
}