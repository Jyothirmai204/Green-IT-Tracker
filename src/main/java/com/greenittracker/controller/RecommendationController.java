package com.greenittracker.controller;

import com.greenittracker.dto.response.ApiResponseDto;
import com.greenittracker.dto.response.RecommendationResponseDto;
import com.greenittracker.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<RecommendationResponseDto>>>
    getRecommendations() {

        List<RecommendationResponseDto> response =
                recommendationService.getAllRecommendations();

        return ResponseEntity.ok(
                ApiResponseDto
                        .<List<RecommendationResponseDto>>builder()
                        .success(true)
                        .message("Recommendations fetched successfully")
                        .status(200)
                        .timestamp(LocalDateTime.now())
                        .data(response)
                        .build()
        );
    }

    @PostMapping("/generate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponseDto<String>>
    generateRecommendations() {

        recommendationService.generateRecommendations();

        return ResponseEntity.ok(
                ApiResponseDto.<String>builder()
                        .success(true)
                        .message("Recommendations generated successfully")
                        .status(200)
                        .timestamp(LocalDateTime.now())
                        .data("SUCCESS")
                        .build()
        );
    }
}