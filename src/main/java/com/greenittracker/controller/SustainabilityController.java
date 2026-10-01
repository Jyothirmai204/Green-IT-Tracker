package com.greenittracker.controller;

import com.greenittracker.dto.response.ApiResponseDto;
import com.greenittracker.dto.response.SustainabilityResponseDto;
import com.greenittracker.service.SustainabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/sustainability")
@RequiredArgsConstructor
public class SustainabilityController {

    private final SustainabilityService sustainabilityService;

    @GetMapping
    public ResponseEntity<ApiResponseDto<SustainabilityResponseDto>>
    getMetrics() {

        SustainabilityResponseDto response =
                sustainabilityService.getLatestMetrics();

        return ResponseEntity.ok(
                ApiResponseDto.<SustainabilityResponseDto>builder()
                        .success(true)
                        .message("Sustainability metrics fetched successfully")
                        .status(200)
                        .timestamp(LocalDateTime.now())
                        .data(response)
                        .build()
        );
    }

    @PostMapping("/calculate")
    public ResponseEntity<ApiResponseDto<SustainabilityResponseDto>>
    calculateMetrics() {

        SustainabilityResponseDto response =
                sustainabilityService.calculateMetrics();

        return ResponseEntity.ok(
                ApiResponseDto.<SustainabilityResponseDto>builder()
                        .success(true)
                        .message("Metrics calculated successfully")
                        .status(200)
                        .timestamp(LocalDateTime.now())
                        .data(response)
                        .build()
        );
    }
}