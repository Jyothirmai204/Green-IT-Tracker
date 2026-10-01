package com.greenittracker.controller;

import com.greenittracker.dto.response.ApiResponseDto;
import com.greenittracker.dto.response.DashboardResponseDto;
import com.greenittracker.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/summary")
    public ResponseEntity<ApiResponseDto<DashboardResponseDto>>
    getSummary() {

        DashboardResponseDto response =
                dashboardService.getDashboardSummary();

        return ResponseEntity.ok(
                ApiResponseDto.<DashboardResponseDto>builder()
                        .success(true)
                        .message("Dashboard summary fetched successfully")
                        .status(200)
                        .timestamp(LocalDateTime.now())
                        .data(response)
                        .build()
        );
    }
}