package com.greenittracker.service;

import com.greenittracker.dto.response.RecommendationResponseDto;

import java.util.List;

public interface RecommendationService {

    List<RecommendationResponseDto> getAllRecommendations();

    void generateRecommendations();
}