package com.greenittracker.service;

import com.greenittracker.dto.request.PaperlessMetricRequestDto;
import com.greenittracker.dto.response.PaperlessMetricResponseDto;

public interface PaperlessService {

    PaperlessMetricResponseDto savePaperlessMetric(
            PaperlessMetricRequestDto requestDto);

    PaperlessMetricResponseDto getLatestPaperlessMetric();
}