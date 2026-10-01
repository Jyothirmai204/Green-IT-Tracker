package com.greenittracker.controller;

import com.greenittracker.dto.request.PaperlessMetricRequestDto;
import com.greenittracker.dto.response.ApiResponseDto;
import com.greenittracker.dto.response.PaperlessMetricResponseDto;
import com.greenittracker.service.PaperlessService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/paperless")
@RequiredArgsConstructor
public class PaperlessController {

    private final PaperlessService paperlessService;

    @PostMapping
    public ResponseEntity<ApiResponseDto<PaperlessMetricResponseDto>>
    createPaperlessMetric(
            @Valid @RequestBody PaperlessMetricRequestDto requestDto) {

        PaperlessMetricResponseDto response =
                paperlessService.savePaperlessMetric(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponseDto
                                .<PaperlessMetricResponseDto>builder()
                                .success(true)
                                .message("Paperless metric saved successfully")
                                .status(201)
                                .timestamp(LocalDateTime.now())
                                .data(response)
                                .build()
                );
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<PaperlessMetricResponseDto>>
    getLatestMetric() {

        PaperlessMetricResponseDto response =
                paperlessService.getLatestPaperlessMetric();

        return ResponseEntity.ok(
                ApiResponseDto
                        .<PaperlessMetricResponseDto>builder()
                        .success(true)
                        .message("Paperless metric fetched successfully")
                        .status(200)
                        .timestamp(LocalDateTime.now())
                        .data(response)
                        .build()
        );
    }
}