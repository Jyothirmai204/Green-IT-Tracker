package com.greenittracker.service;

import com.greenittracker.dto.response.SustainabilityResponseDto;

public interface SustainabilityService {

    SustainabilityResponseDto getLatestMetrics();

    SustainabilityResponseDto calculateMetrics();
}