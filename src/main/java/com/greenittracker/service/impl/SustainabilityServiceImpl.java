package com.greenittracker.service.impl;

import com.greenittracker.dto.response.SustainabilityResponseDto;
import com.greenittracker.entity.SustainabilityMetric;
import com.greenittracker.exception.SustainabilityCalculationException;
import com.greenittracker.mapper.SustainabilityMapper;
import com.greenittracker.repository.SustainabilityMetricRepository;
import com.greenittracker.service.AuditLogService;
import com.greenittracker.service.SustainabilityService;
import com.greenittracker.util.CarbonCalculatorUtil;
import com.greenittracker.util.GreenScoreCalculator;
import com.greenittracker.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class SustainabilityServiceImpl
        implements SustainabilityService {

    private final SustainabilityMetricRepository
            sustainabilityMetricRepository;

    private final SustainabilityMapper sustainabilityMapper;

    private final AuditLogService auditLogService;

    @Override
    @Transactional(readOnly = true)
    public SustainabilityResponseDto getLatestMetrics() {

        SustainabilityMetric metric =
                sustainabilityMetricRepository
                        .findAll()
                        .stream()
                        .findFirst()
                        .orElse(null);

        if (metric == null) {

            return SustainabilityResponseDto.builder()
                    .energySaved(0.0)
                    .carbonSaved(0.0)
                    .costSaved(0.0)
                    .greenScore(0)
                    .build();
        }

        return sustainabilityMapper.toResponseDto(
                metric
        );
    }

    @Override
    public SustainabilityResponseDto calculateMetrics() {

        try {

            double energySaved = 1500.0;

            double carbonSaved =
                    CarbonCalculatorUtil
                            .calculateCarbonSaved(
                                    energySaved
                            );

            int greenScore =
                    GreenScoreCalculator
                            .calculateGreenScore(
                                    80,
                                    85,
                                    90,
                                    75,
                                    95
                            );

            SustainabilityMetric metric =
                    SustainabilityMetric.builder()
                            .energySaved(energySaved)
                            .carbonSaved(carbonSaved)
                            .costSaved(50000.0)
                            .greenScore(greenScore)
                            .metricDate(LocalDate.now())
                            .build();

            sustainabilityMetricRepository.save(
                    metric
            );

            auditLogService.saveAuditLog(
                    SecurityUtil.getLoggedInUser(),
                    "CALCULATE",
                    "SUSTAINABILITY",
                    "Sustainability metrics calculated"
            );

            return sustainabilityMapper
                    .toResponseDto(metric);

        } catch (Exception e) {

            throw new SustainabilityCalculationException(
                    "Error calculating sustainability metrics"
            );
        }
    }
}