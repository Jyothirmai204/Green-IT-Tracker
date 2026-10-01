package com.greenittracker.service.impl;

import com.greenittracker.dto.response.DashboardResponseDto;
import com.greenittracker.repository.RecommendationRepository;
import com.greenittracker.repository.ResourceRepository;
import com.greenittracker.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl
        implements DashboardService {

    private final ResourceRepository resourceRepository;

    private final RecommendationRepository
            recommendationRepository;

    @Override
    public DashboardResponseDto
    getDashboardSummary() {

        return DashboardResponseDto.builder()
                .totalResources(
                        resourceRepository.count()
                )
                .recommendationsCount(
                        recommendationRepository.count()
                )
                .greenScore(85)
                .energySaved(1500.00)
                .carbonSaved(500.00)
                .costSaved(20000.00)
                .build();
    }
}