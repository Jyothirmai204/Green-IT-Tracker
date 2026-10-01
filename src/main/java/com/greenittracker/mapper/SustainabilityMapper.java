package com.greenittracker.mapper;

import com.greenittracker.dto.response.SustainabilityResponseDto;
import com.greenittracker.entity.SustainabilityMetric;
import org.springframework.stereotype.Component;

@Component
public class SustainabilityMapper {

    public SustainabilityResponseDto toResponseDto(
            SustainabilityMetric metric) {

        return SustainabilityResponseDto.builder()
                .energySaved(metric.getEnergySaved())
                .carbonSaved(metric.getCarbonSaved())
                .costSaved(metric.getCostSaved())
                .greenScore(metric.getGreenScore())
                .build();
    }
}
