package com.greenittracker.service.impl;

import com.greenittracker.dto.response.RecommendationResponseDto;
import com.greenittracker.entity.Recommendation;
import com.greenittracker.entity.Resource;
import com.greenittracker.enums.ResourceStatus;
import com.greenittracker.exception.RecommendationGenerationException;
import com.greenittracker.mapper.RecommendationMapper;
import com.greenittracker.repository.RecommendationRepository;
import com.greenittracker.repository.ResourceRepository;
import com.greenittracker.service.AuditLogService;
import com.greenittracker.service.RecommendationService;
import com.greenittracker.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RecommendationServiceImpl
        implements RecommendationService {

    private final RecommendationRepository recommendationRepository;

    private final ResourceRepository resourceRepository;

    private final RecommendationMapper recommendationMapper;

    private final AuditLogService auditLogService;

    @Override
    @Transactional(readOnly = true)
    public List<RecommendationResponseDto>
    getAllRecommendations() {

        return recommendationRepository.findAll()
                .stream()
                .map(recommendationMapper::toResponseDto)
                .toList();
    }

    @Override
    public void generateRecommendations() {

        try {

            List<Resource> resources =
                    resourceRepository.findByStatus(
                            ResourceStatus.IDLE
                    );

            for (Resource resource : resources) {

                Recommendation recommendation =
                        Recommendation.builder()
                                .resource(resource)
                                .issue("Idle Resource Detected")
                                .recommendation(
                                        "Decommission or optimize resource"
                                )
                                .estimatedSavings(5000.0)
                                .priority("HIGH")
                                .generatedAt(LocalDateTime.now())
                                .build();

                recommendationRepository.save(
                        recommendation
                );
            }

            auditLogService.saveAuditLog(
                    SecurityUtil.getLoggedInUser(),
                    "GENERATE",
                    "RECOMMENDATION",
                    "Recommendations generated successfully"
            );

        } catch (Exception e) {

            throw new RecommendationGenerationException(
                    "Failed to generate recommendations"
            );
        }
    }
}