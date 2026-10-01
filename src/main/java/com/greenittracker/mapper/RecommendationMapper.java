package com.greenittracker.mapper;

import com.greenittracker.dto.response.RecommendationResponseDto;
import com.greenittracker.entity.Recommendation;
import org.springframework.stereotype.Component;

@Component
public class RecommendationMapper {

    public RecommendationResponseDto toResponseDto(
            Recommendation recommendation) {

        return RecommendationResponseDto.builder()
                .id(recommendation.getId())
                .issue(recommendation.getIssue())
                .recommendation(recommendation.getRecommendation())
                .estimatedSavings(recommendation.getEstimatedSavings())
                .priority(recommendation.getPriority())
                .build();
    }
}